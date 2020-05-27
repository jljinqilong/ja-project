package com.champlink.sale.service.customer;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.customer.CustomerExcelForm;
import com.champlink.common.form.sale.customer.CustomerForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.area.AreaDao;
import com.champlink.sale.dao.customer.CustomerContactsDao;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;

@Service
public class CustomerService {


    private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerContactsDao customerContactsDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private StaffService staffService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private AreaDao areaDao;
    @Autowired
	private RedisService redisService;
    

    /**
      * 添加
      * @author created by barrett in 18-8-1 下午3:18
      */
    @Transactional
    public boolean add(Customer customer) {
        //效验客户名是否存在
        if(customerDao.checkCustomerByName(customer) > 0){
            AppException.create(300000);//客户简称或名称已存在
        }
        //根据国家区域查询币别
        Long currencyId = customerDao.getCurrencyId(customer.getAreaId(), customer.getCountryId());
        customer.setCurrencyId(currencyId);
        if (customerDao.add(customer) > 0) {
            if(customer.getCustomerContactsList() != null && !customer.getCustomerContactsList().isEmpty()){
                for (CustomerContacts cc: customer.getCustomerContactsList()) {
                    cc.setCreatedBy(customer.getCreatedBy());
                    cc.setCustomerId(customer.getRowId());
                }
                //添加联系人
                customerContactsDao.insertContactsBatch(customer.getCustomerContactsList());
            }
            return true;
        }
        return false;
    }
    /**
      * 分页查询
      * @author created by barrett in 18-8-1 下午3:19
      */
    public PageListVO<Customer> pageList(CustomerForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Customer> pageListVO = PageListVO.newInstance(customerDao.pageList(paginater));
        return pageListVO;
    }
    /**
      * 修改
      * @author created by barrett in 18-8-3 下午5:22
      */
    @Transactional
    public boolean update(Customer customer) {
        //效验客户名简称是否存在
        if(customerDao.checkCustomerByName(customer) > 0){
            AppException.create(300000);//客户简称或名称已存在
        }
        if (customerDao.update(customer) > 0) {
            //1、删除原来客户的联系人
            String[] idArr = {String.valueOf(customer.getRowId())};
            customerContactsDao.deleteContactsByCustomerId(idArr);
            if(customer.getCustomerContactsList() != null && !customer.getCustomerContactsList().isEmpty()){
                for (CustomerContacts cc: customer.getCustomerContactsList()) {
                    cc.setCreatedBy(customer.getCreatedBy());
                    cc.setCustomerId(customer.getRowId());
                }
                //2、添加联系人
                customerContactsDao.insertContactsBatch(customer.getCustomerContactsList());
            }
            return true;
        }
        return false;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Transactional
    public void deleteByIds(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			List<Long> list = Arrays.asList(ids.split(",")).stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
			//只能删除新增状态的客户信息
			if (customerDao.getCustomerStatus(idArr) > 0) {
				AppException.create(300001);//只能删除新增状态的客户
			}
			try{
				//删除客户
				if (customerDao.deleteCustomerById(idArr) > 0) {
					//删除客户的联系人
					customerContactsDao.deleteContactsByCustomerId(idArr);
				}
			} catch (DataIntegrityViolationException e) {
				throw new AppException("当前客户被引用，无法删除");
			}
		}

    }

    /**
     * 只查单表联系人信息
     * @Author created by barrett in 18-8-9 下午2:47
     */
    public Customer getCustomerInfo(Long rowId){
        Customer customer = customerDao.getCustomerById(rowId);
        return customer;
    }

    /**
     * 查询联系人和客户信息
     * @Author created by barrett in 18-8-9 下午2:47
     */
    public Customer getCustomerAndContacts(Long rowId){
        Customer customer = customerDao.getCustomerAndContacts(rowId);
        try{
            //查询用户名称
            String userInfo = staffService.getUserInfo(customer.getCreatedBy());
            BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
            customer.setCreateName(user.getStaffName());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return customer;
    }

    /**
     * 获取所有客户
     * @Author created by barrett in 18-8-10 上午10:00
     */
    public List<BaseSelect> getAllCustomer(){
        List<BaseSelect> list = customerDao.getAllCustomer();
        return list;
    }
    
    /***
     * customer Excel 导入数据入库
     * @param list
     * @return
     */
	public String importExcelCustomer(List<CustomerExcelForm> list){
    	
		// 获取当前操作人
//		String token = RequestContext.get().getToken();
//		User loginUser = redisService.getLoginUserByToken(token);
		List<Customer> customers = new ArrayList<Customer>();
		List<String> strs = new ArrayList<String>();
		
		//字典翻译账期单位
		String accountUnit = saleCommonService.getByTpye("ACCOUNT_UNIT");
		JSONObject parseObject1 = JSONObject.parseObject(accountUnit);
		List<Code> allCodeList1 = JSONObject.parseArray(parseObject1.getString("data"), Code.class);
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(!CollectionUtils.isEmpty(allCodeList1)){
			for (Code code : allCodeList1) {
				map.put("ACCOUNT_UNIT-"+code.getCode(), code.getRowId());
			}
		}
		
		//字典翻译客户等级
		String customerLevel = saleCommonService.getByTpye("CUSTOMER_LEVEL");
		JSONObject parseObject3 = JSONObject.parseObject(customerLevel);
		List<Code> allCodeList3 = JSONObject.parseArray(parseObject3.getString("data"), Code.class);
		if(!CollectionUtils.isEmpty(allCodeList3)){
		  for (Code code : allCodeList3) {
			  map.put("CUSTOMER_LEVEL-"+code.getCode(), code.getRowId());
		  }
		}
		
		//字典翻译付款方式
		String paymentType = saleCommonService.getByTpye("PAYMENT_TYPE");
		JSONObject parseObject4 = JSONObject.parseObject(paymentType);
		List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
		if(!CollectionUtils.isEmpty(allCodeList4)){
			for (Code code : allCodeList4) {
				map.put("PAYMENT_TYPE-"+code.getCode(), code.getRowId());
			}
		}
		//字典翻译币别
		String currency = saleCommonService.getByTpye("CURRENCY");
		JSONObject parseObject5 = JSONObject.parseObject(currency);
		List<Code> allCodeList5 = JSONObject.parseArray(parseObject5.getString("data"), Code.class);
		if(!CollectionUtils.isEmpty(allCodeList5)){
			for (Code code : allCodeList5) {
				map.put("CURRENCY-"+code.getCode(), code.getRowId());
			}
		}
		
		for (CustomerExcelForm customerExcelForm : list) {
			
			Customer c = new Customer();
			
			Object oAccountUnitId = map.get("ACCOUNT_UNIT-"+customerExcelForm.getAccountUnitId());
			Object oCustomerLevel = map.get("CUSTOMER_LEVEL-"+customerExcelForm.getCustomerLevel());
			Object oPaymentTypeId = map.get("PAYMENT_TYPE-"+customerExcelForm.getPaymentTypeId());
			Object oJaCurrency= map.get("CURRENCY-"+customerExcelForm.getJaCurrency());
			Object oZxbCurrency= map.get("CURRENCY-"+customerExcelForm.getZxbCurrency());
			//翻译区域名称
		    Area area = areaDao.getArea(customerExcelForm.getRegionName());
		    
			if(oAccountUnitId==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的账期单位不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的账期单位不合规范");
			}
			if(oCustomerLevel==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的客户等级不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的客户等级不合规范");
			}
			if(oPaymentTypeId==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的付款方式不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的付款方式不合规范");
			}
			if(oJaCurrency==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的晶澳币别币不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的晶澳币别币不合规范");
			}
			if(oZxbCurrency==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的中信保币别不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的中信保币别不合规范");
			}

			if(area.getRowId()==null){
//				return "序列为："+customerExcelForm.getOrder()+" 的区域名称不合规范";
				throw new AppException("序列为："+customerExcelForm.getOrder()+" 的区域名称不合规范");
			}
			
			c.setCreatedBy(111111L);
//			c.setCreatedBy(loginUser.getRowId());
			c.setCustomerName(customerExcelForm.getCustomerName());
			c.setShortName(customerExcelForm.getShortName());
			c.setShortEnName(customerExcelForm.getShortEnName());
			c.setAddress(customerExcelForm.getAddress());
			c.setPaymentDays(Integer.valueOf(customerExcelForm.getPaymentDays()));
			c.setAccountUnitId((Long) oAccountUnitId);
			c.setAreaId(area.getRowId());
			c.setPostalCode(customerExcelForm.getPostalCode());
			c.setFax(customerExcelForm.getFax());
			c.setCustomerLevelId((Long) oCustomerLevel);
			c.setDutyParagraph(customerExcelForm.getDutyParagraph());
			c.setInvoiceSendingAddress(customerExcelForm.getInvoiceSendingAddress());
			c.setInvoiceSendingPcode(customerExcelForm.getInvoiceSendingPcode());
			c.setDeliveryAddress(customerExcelForm.getDeliveryAddress());
			c.setBankAccount(customerExcelForm.getBankAccount());
			c.setRegTime(customerExcelForm.getRegTime());
			c.setRegCapital(customerExcelForm.getRegCapital());
			c.setPaymentTypeId((Long) oPaymentTypeId);
			c.setWebSite(customerExcelForm.getWebSite());
			c.setCustomerInfo(customerExcelForm.getCustomerInfo());
			c.setDebtLimit(Double.valueOf(customerExcelForm.getDebtLimit()));
			c.setZxbArrears(new BigDecimal(customerExcelForm.getZxbArrears()));
			c.setJaCurrencyId((Long) oJaCurrency);
			c.setZxbCurrencyId((Long) oZxbCurrency);
			customers.add(c);
			
			strs.add(customerExcelForm.getCustomerName());
			
		}
		
		// 1.校验产品型号是否存在
		List<Customer> checkCustomerNoIsExist = customerDao.checkCustomerNoIsExist(strs);
		StringBuilder str = new StringBuilder();
		str.append("序列：");
		
		if (!CollectionUtils.isEmpty(checkCustomerNoIsExist)) {
			for (Customer customer : checkCustomerNoIsExist) {
				int indexOf = strs.indexOf(customer.getCustomerName());
				str.append(""+(indexOf+1)+",");
			}
			String substring = str.substring(0, str.length()-1);
			substring = substring+" 的客户名称已存在！";
			throw new AppException(substring);
			
		}
		// 2.批量导入产品信息
		customerDao.insertByForeachCustomer(customers);
    	
    	return null;
    }

    /**
     * @Author jsl
     * @Date 15:19 2018/8/25
     * @Description    根据staffId查询客户列表
     **/
	public PageListVO<Customer> getCustomerListById(CustomerForm form) {
		Paginater paginater = Paginater.newInstance(form);
		PageListVO<Customer> pageListVO = PageListVO.newInstance(customerDao.getCustomerListById(paginater));
		return pageListVO;
	}

	/**
	 * @Author jsl
	 * @Date 17:35 2018/8/25
	 * @Description    更新transferMan
	 **/
	public boolean updateTransferMan(Long staffId, Long rowId) {
		if(customerDao.updateTransferMan(staffId,rowId)){
			return true;
		}
		return false;
	}
}
