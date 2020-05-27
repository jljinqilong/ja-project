package com.champlink.sale.controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.product.Product;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.sale.product.ImportProductForm;
import com.champlink.common.form.sale.product.SaleProductForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.product.ProductDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.product.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController{
   
	@Autowired
	private ProductService productService;
	
	@Autowired
    private SaleCommonService saleCommonService;
	
	@Autowired
	private ProductDao productDao;
	
	
	/**
	 * 按照条件查询数据
	 * @param saleProductForm
	 * @return
	 */
	@RequestMapping(value = "/list",produces = "text/json;charset=UTF-8")
    public String searchSaleContractList(SaleProductForm saleProductForm){
        PageListVO<Product> pageListVO = productService.searchSaleProductList(saleProductForm);
        //id转name
        String allCode = saleCommonService.getAllCode();
        List<Product> detailList = pageListVO.getList();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                detailList = super.translateIdToName(detailList,allCodeList,new String[]{
                        "siliconTypeId,rowId,name",
                        "cellNumberId,rowId,name",
                        "muduleTypeId,rowId,name",
                        "muduleCodeId,rowId,name",
                        "cellTechnologyId,rowId,name"});
            }
        }
        pageListVO.setList(detailList);
        return getSuccessJson(pageListVO);
		
    }
	
	/**
	 * 增加一个产品
	 * @param
	 * @return
	 */
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String addRegion(@Valid @RequestBody SaleProductForm saleProductForm){
        if (productService.addProduct(saleProductForm)){
            return getSuccessJson();
        }
        return getFailJson();
    } 
	
	/**
	 * 根据rowId删除产品
	 * @param rowId
	 * @return
	 */
	@PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long rowId) {
        if (productService.deleteProductByRowId(rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
	
	/**
	 * 修改产品信息
	 * @param product
	 * @return
	 */
	@PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody Product product) {
        if (productService.updateProductByRowId(product)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
	
	/**
	 * 查询一个产品
	 * @param rowId
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        Product queryProductByRowId = productService.queryProductByRowId(rowId);
		//id转name
		String allCode = saleCommonService.getAllCode();
		if (allCode != null) {
			JSONObject parseObject = JSONObject.parseObject(allCode);
			if ((Integer) parseObject.get("code") == 200) {
				List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
				queryProductByRowId = (Product)super.translateIdToNameObj(queryProductByRowId,allCodeList,new String[]{
						"siliconTypeId,rowId,name",
						"cellNumberId,rowId,name",
						"muduleTypeId,rowId,name",
						"muduleCodeId,rowId,name",
						"cellTechnologyId,rowId,name"});
			}
		}
        return getSuccessJson(queryProductByRowId);
    }
	
	/**
	 * 查询所有产品的id,型号,功率
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    public String getAll() {
        List<Product> productByProduct = productDao.getAll();
        return getSuccessJson(productByProduct);
    }
	
	
	
	/**
	 *  导入产品信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1.构建Excel接受的实体类
        List<ImportProductForm> list = new ArrayList<ImportProductForm>();
        
        //2.将Excel转成JavaBean ImportProductForm
        ImportExcelUtils.export(request, ImportProductForm.class, list,1,0);
        
        //3.将ImportProductForm入库到Product
        String importExcelProduct = productService.importExcelProduct(list);
        
        if(importExcelProduct==null){
        	return getSuccessJson();
        }else{
        	return  getFailJson(importExcelProduct);
        }
        
        
    }
	
	
	/**
	 * 导出产品模板
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcel", produces = "application/json;charset=UTF-8")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response){
		
		File outputFile = new File("");
		
		//返回excel的文件流
		try {
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename=productModel"
			    + new SimpleDateFormat("yyyyMMdd_HHmmssSSS")
			              .format(new Date()) +".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
 
			// 读取文件并且输出
			FileInputStream fin = new FileInputStream(outputFile);
			byte[] tempBytes = new byte[2048];
			while (fin.read(tempBytes) != -1) {
				response.getOutputStream().write(tempBytes);
			}
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据rowId和status更改产品状态
	 * @param rowId
	 * @return
	 */
	@PostMapping(value = "/updateStatus/{id}/{status}", produces = "application/json;charset=UTF-8")
	public String updateStatus(@PathVariable("id") Long rowId,@PathVariable("status") String status) {
		if (productService.updateProductByRowIdAndStatus(rowId,status)) {
			return getSuccessJson();
		}
		return getFailJson();
	}
	
	/**
	 * @Author WJ
     * @Description    下载模板
     */
    @RequestMapping(value = "/download", produces = "application/json;charset=UTF-8")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }
	
	
}




