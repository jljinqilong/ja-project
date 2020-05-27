package com.champlink.sale.service.customer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.champlink.common.util.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.constant.Constant;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.common.form.sale.customer.CustomerContactsExcelForm;
import com.champlink.common.form.sale.customer.CustomerForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.customer.CustomerContactsDao;
import com.champlink.sale.dao.customer.CustomerDao;

@Service
public class CustomerContactsService {

    @Autowired
    private CustomerContactsDao customerContractsDao;
    @Autowired
    private CustomerDao customerDao;
    
    private static Logger logger = LoggerFactory.getLogger(CustomerContactsService.class);
    /**
      * 添加
      * @author created by barrett in 18-8-1 下午3:18
      */
    public boolean add(CustomerContacts customerContacts) {

        if (customerContractsDao.add(customerContacts) > 0) {
            return true;
        }
        return false;
    }
    /**
      * 分页查询
      * @author created by barrett in 18-8-1 下午3:19
      */
    public PageListVO<CustomerContacts> pageList(CustomerForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<CustomerContacts> pageListVO = PageListVO.newInstance(customerContractsDao.pageList(paginater));
        return pageListVO;
    }
    /**
      * 修改
      * @author created by barrett in 18-8-3 下午5:22
      */
    public boolean update(CustomerContacts customerContacts) {

        if (customerContractsDao.update(customerContacts) > 0) {
            return true;
        }
        return false;
    }

    public List<CustomerContacts> getContactsByCustomerId(Long customerId){
        List<CustomerContacts> list = customerContractsDao.getContactsByCustomerId(customerId);
        return list;
    }
    
    /***
     * customerContacts Excel 导入数据入库
     * @param list
     * @return
     */
    public String importExcelCustomerContacts(List<CustomerContactsExcelForm> list){
    	
    	try {
			List<CustomerContacts> contacts = new ArrayList<CustomerContacts>();
			HashSet<String> strSet = new HashSet<String>();
			List<String> strsList = new ArrayList<String>();
			HashMap<String,Object> map = new HashMap<String,Object>();
			for (CustomerContactsExcelForm customerContactsExcelForm : list) {
				strSet.add(customerContactsExcelForm.getCustomeName());
			}
			
			strsList.addAll(strSet);
			
			//1.查询客户名称所对应的Id
			List<Customer> checkCustomerNoIsExist = customerDao.checkCustomerNoIsExist(strsList);
			for (Customer customer : checkCustomerNoIsExist) {
				map.put(customer.getCustomerName(), customer.getRowId());
			}
			
			
			for (CustomerContactsExcelForm customerContactsExcelForm : list) {
				
				Object object = map.get(customerContactsExcelForm.getCustomeName());
				
				if(object==null){
//					return "序列为："+customerContactsExcelForm.getOrder()+" 的客户姓名不存在";
					throw new AppException("序列为："+customerContactsExcelForm.getOrder()+" 的客户姓名不存在");
				}
				
				CustomerContacts customerContacts = new CustomerContacts();
//			customerContacts.setCreatedBy(createdBy);
				customerContacts.setCustomerId((long) object);
				customerContacts.setName(customerContactsExcelForm.getName());
				customerContacts.setPosition(customerContactsExcelForm.getPosition());
				customerContacts.setMobile(customerContactsExcelForm.getMobile());
				customerContacts.setMail(customerContactsExcelForm.getMail());
				customerContacts.setTel(customerContactsExcelForm.getTel());
				customerContacts.setDelFlag(Constant.DEL_FLAG_VALID);
				customerContacts.setStatus(SaleConstant.ENABLED_FLAG_VALID);
				contacts.add(customerContacts);
			}
			
			customerContractsDao.insertContactsBatch(contacts);
		} catch (Exception e) {
			logger.error("导入Excel失败  : " + e.getMessage());
//			return "导入Excel失败 ！ 数据操作异常请查看相关日志。";
			throw new AppException("导入Excel失败 ！ 数据操作异常请查看相关日志。");
		}
		
		
    	return null;
    }
}
