package com.champlink.sale.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.product.Product;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.product.ImportProductForm;
import com.champlink.common.form.sale.product.SaleProductForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.product.ProductDao;
import com.champlink.sale.service.call.SaleCommonService;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private SaleCommonService saleCommonService;

	@Autowired
	private RedisService redisService;

	private static Logger logger = LoggerFactory.getLogger(ProductService.class);

	public PageListVO<Product> searchSaleProductList(SaleProductForm saleProductForm) {
		Paginater paginater = Paginater.newInstance(saleProductForm);
		// 区域list
		PageListVO<Product> listVO = PageListVO.newInstance(productDao.searchSaleProductList(paginater));
		return listVO;
	}

	/**
	 * 根据产品型号查询产品
	 *
	 * @param productNo
	 * @return
	 */
	public Product queryProductByProductNo(String productNo) {
		Product p = new Product();
		p.setProductNo(productNo);
		List<Product> productByProduct = productDao.getProductByProduct(p);
		if (!CollectionUtils.isEmpty(productByProduct)) {
			return productByProduct.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据产品型号查询产品
	 *
	 * @param
	 * @return
	 */
	public Product queryProductByRowId(Long rowId) {
		Product p = new Product();
		p.setRowId(rowId);
		List<Product> productByProduct = productDao.getProductByProduct(p);
		if (!CollectionUtils.isEmpty(productByProduct)) {
			return productByProduct.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据产品RowId删除该产品
	 *
	 * @param
	 * @return
	 */
	@Transactional
	public Boolean deleteProductByRowId(Long rowId) {
		productDao.delProductByRowId(rowId);
		return Boolean.TRUE;
	}

	/**
	 * 根据产品RowId修改产品信息
	 *
	 * @param product
	 * @return
	 */
	@Transactional
	public Boolean updateProductByRowId(Product product) {
		productDao.updateProductByRowId(product);
		return Boolean.TRUE;
	}

	/**
	 * 增加一个产品
	 *
	 * @return
	 */
	@Transactional
	public Boolean addProduct(SaleProductForm saleProductForm) {

		// 1.生成产品型号
		StringBuilder productNo = new StringBuilder();
		productNo.append(SaleConstant.INQUIRY_PREFIX).append(saleProductForm.getSiliconType())
				.append(saleProductForm.getCellNumber()).append(saleProductForm.getMuduleType())
				.append(saleProductForm.getMuduleCode()).append("-").append(saleProductForm.getRatedPower()).append("/")
				.append(saleProductForm.getCellTechnology());

		// 1.校验产品型号是否唯一
		Product p = new Product();
		p.setProductNo(productNo.toString());
		List<Product> productByProduct = productDao.getProductByProduct(p);
		if (!CollectionUtils.isEmpty(productByProduct)) {
			logger.error(productNo + " : 产品型号已存在！ ");
			AppException.create(320001);
			return Boolean.FALSE;
		}

		String token = RequestContext.get().getToken();
		User loginUser = redisService.getLoginUserByToken(token);
		p.setBomId(saleProductForm.getBomId());
		p.setBomNo(saleProductForm.getBomNo());
		p.setProductNo(p.getProductNo());
		p.setSiliconTypeId(saleProductForm.getSiliconTypeId());
		p.setCellNumberId(saleProductForm.getCellNumberId());
		p.setMuduleTypeId(saleProductForm.getMuduleTypeId());
		p.setMuduleCodeId(saleProductForm.getMuduleCodeId());
		p.setRatedPower(saleProductForm.getRatedPower());
		p.setCellTechnologyId(saleProductForm.getCellTechnologyId());
		p.setStatus(SaleConstant.ENABLED_FLAG_VALID);
		p.setDelFlag(Constant.DEL_FLAG_VALID);
		p.setCreatedBy(loginUser.getRowId());
		productDao.insertProduct(p);

		return Boolean.TRUE;
	}

	/**
	 * 将ImportProductForm 转化为Product对象入库
	 *
	 * @param form
	 * @return
	 */
	public String importExcelProduct(List<ImportProductForm> form) {

		//获取当前操作人
		String token = RequestContext.get().getToken();
		User loginUser = redisService.getLoginUserByToken(token);
		List<Product> products = new ArrayList<Product>();
		List<String> strs = new ArrayList<String>();

		// 字典翻译硅片类型
		String siliconType = saleCommonService.getByTpye("SILICON_TYPE");
		JSONObject parseObject1 = JSONObject.parseObject(siliconType);
        List<Code> allCodeList1 = JSONObject.parseArray(parseObject1.getString("data"), Code.class);
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(!CollectionUtils.isEmpty(allCodeList1)){
        	for (Code code : allCodeList1) {
        		map.put("SILICON_TYPE-"+code.getCode(), code.getRowId());
			}
        }
        //字典翻译电池片数
        String cellNumber = saleCommonService.getByTpye("CELL_NUMBER");
        JSONObject parseObject2 = JSONObject.parseObject(cellNumber);
        List<Code> allCodeList2 = JSONObject.parseArray(parseObject2.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList2)){
        	for (Code code : allCodeList2) {
        		map.put("CELL_NUMBER-"+code.getCode(), code.getRowId());
			}
        }
        //字典翻译组件类型
        String muduleType = saleCommonService.getByTpye("MUDULE_TYPE");
        JSONObject parseObject3 = JSONObject.parseObject(muduleType);
        List<Code> allCodeList3 = JSONObject.parseArray(parseObject3.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList3)){
        	for (Code code : allCodeList3) {
        		map.put("MUDULE_TYPE-"+code.getCode(), code.getRowId());
			}
        }

        //字典翻译组件关键信息码
        String muduleCode = saleCommonService.getByTpye("MUDULE_CODE");
        JSONObject parseObject4 = JSONObject.parseObject(muduleCode);
        List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList4)){
        	for (Code code : allCodeList4) {
        		map.put("MUDULE_CODE-"+code.getCode(), code.getRowId());
			}
        }

        //字典翻译电池片技术
        String cellTechnology = saleCommonService.getByTpye("CELL_TECHNOLOGY");
        JSONObject parseObject5 = JSONObject.parseObject(cellTechnology);
        List<Code> allCodeList5 = JSONObject.parseArray(parseObject5.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList5)){
        	for (Code code : allCodeList5) {
        		map.put("CELL_TECHNOLOGY-"+code.getCode(), code.getRowId());
			}
        }

		for (ImportProductForm importProductForm : form) {
			Product p = new Product();
			StringBuilder productNo = new StringBuilder();
			productNo.append(SaleConstant.INQUIRY_PREFIX).append(importProductForm.getSiliconType())
			.append(importProductForm.getCellNumber()).append(importProductForm.getMuduleType())
			.append(importProductForm.getMuduleCode()).append("-").append(importProductForm.getRatedPower()).append("/")
			.append(importProductForm.getCellTechnology());

			strs.add(productNo.toString());
			p.setProductNo(productNo.toString());

			Object oSiliconType = map.get("SILICON_TYPE-"+importProductForm.getSiliconType());
			Object oCellNumber = map.get("CELL_NUMBER-"+importProductForm.getCellNumber());
			Object oMuduleType = map.get("MUDULE_TYPE-"+importProductForm.getMuduleType());
			Object oMuduleCode= map.get("MUDULE_CODE-"+importProductForm.getMuduleCode());
			Object oCellTechnology = map.get("CELL_TECHNOLOGY-"+importProductForm.getCellTechnology());

			if(oSiliconType==null){
//				return "序列为："+importProductForm.getOrder()+" 的硅片类型不合规范";
				throw new AppException("序列为："+importProductForm.getOrder()+" 的硅片类型不合规范");
			}
			if(oCellNumber==null){
//				return "序列为："+importProductForm.getOrder()+" 的电池片数不合规范";
				throw new AppException("序列为："+importProductForm.getOrder()+" 的电池片数不合规范");
			}
			if(oMuduleType==null){
//				return "序列为："+importProductForm.getOrder()+" 的组件类型不合规范";
				throw new AppException("序列为："+importProductForm.getOrder()+" 的组件类型不合规范");
			}
			if(oMuduleCode==null){
//				return "序列为："+importProductForm.getOrder()+" 的组件关键信息码不合规范";
				throw new AppException("序列为："+importProductForm.getOrder()+" 的组件关键信息码不合规范");
			}
			if(oCellTechnology==null){
//				return "序列为："+importProductForm.getOrder()+" 的电池片技术不合规范";
				throw new AppException("序列为："+importProductForm.getOrder()+" 的电池片技术不合规范");
			}

			//功率档不能为空
			if(importProductForm.getRatedPower() == null ){
				throw new AppException("序列为："+importProductForm.getOrder()+" 的功率档不能为空");
			}

			//正则表达式 取小于7位的正整数
			String str = "^[1-9]{1}[0-9]{0,5}?$";//匹配整数11位，小数6位的正则表达式
			if(!importProductForm.getRatedPower().matches(str)){
				throw new AppException("序列为："+importProductForm.getOrder()+" 的功率档不能超过7位数的正整数");
			}


			p.setSiliconTypeId((Long)oSiliconType);
			p.setCellNumberId((Long)oCellNumber);
			p.setMuduleTypeId((Long)oMuduleType);
			p.setMuduleCodeId((Long)oMuduleCode);
			p.setCellTechnologyId((Long)oCellTechnology);

			p.setRatedPower(importProductForm.getRatedPower());
			p.setStatus(SaleConstant.ENABLED_FLAG_VALID);
			p.setDelFlag(Constant.DEL_FLAG_VALID);
			p.setCreatedBy(loginUser.getRowId());
//			p.setCreatedBy(11111L);
			products.add(p);

		}

		// 1.校验产品型号是否存在
		List<Product> checkProductNoIsExist = productDao.checkProductNoIsExist(strs);
		StringBuilder str = new StringBuilder();
		str.append("序列：");
		if (!CollectionUtils.isEmpty(checkProductNoIsExist)) {
			for (Product product : checkProductNoIsExist) {
				int indexOf = strs.indexOf(product.getProductNo());
				str.append(""+(indexOf+1)+",");
			}
			String substring = str.substring(0, str.length()-1);
			substring = substring+" 的产品型号已存在！";
			throw new AppException(substring);

		}
		// 2.批量导入产品信息
		productDao.insertByForeachProdect(products);

		return null;
	}

	public static void main(String[] args) {
		StringBuilder b = new StringBuilder("1,2,");
		String substring = b.substring(0, b.length()-1);

		System.out.println(substring);
	}

	/**
	 * @Author jsl
	 * @Date 根据rowId和status更改产品状态 2018/8/23
	 * @Description
	 **/
	public boolean updateProductByRowIdAndStatus(Long rowId, String status) {
		if(productDao.updateProductByIdAndStatus(rowId,status)){
			return true;
		}
		return false;
	}
}
