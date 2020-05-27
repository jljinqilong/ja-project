package com.champlink.sale;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.common.form.sale.contract.SaleContractForm;
import com.champlink.common.form.sale.customer.CustomerContactsExcelForm;
import com.champlink.common.form.sale.customer.CustomerExcelForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.sale.dao.customer.CustomerContactsDao;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.contract.SaleContractService;
import com.champlink.sale.service.customer.CustomerContactsService;
import com.champlink.sale.service.customer.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerAppTests extends BaseController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CustomerContactsService customerContactsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerContactsDao customerContactsDao;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private SaleContractService saleContractService;

    @Test
    public void add() {
        Customer customer = new Customer();
        customer.setCustomerName("李四1");
        customer.setShortName("简称1");
        customer.setAddress("北京市东直门外XXX号");
        customer.setPaymentDays(3);

        customer.setPostalCode("邮编");
        customer.setFax("传真");
        customer.setDutyParagraph("税号");
        customer.setInvoiceSendingAddress("发票地址");
        customer.setInvoiceSendingPcode("发票邮编");
        customer.setDeliveryAddress("收货地址");
        customer.setBankAccount("6000 5000");
        customer.setRegTime("2018-08");
        customer.setRegCapital("注册资本");
        customer.setDebtLimit(12.3);
        customer.setMobile("123456789000");
        customer.setFixedPhone("123456");
        customer.setWebSite("www.xx");
        customer.setCurrencyId(1l);
        customer.setCustomerInfo("客户信息");

        customer.setAreaId(7l);
        customer.setAccountUnitId(137l);
        customer.setCustomerLevelId(143l);
        customer.setPaymentTypeId(140l);
        customerService.add(customer);
    }
    
    @Test
    public void addBatch() {
    	
    	List<Customer> customers = new ArrayList<Customer>();
    	
    	for (int i = 0; i < 2; i++) {
	        Customer customer = new Customer();
	        customer.setCustomerName("李四"+i);
	        customer.setShortName("简称"+i);
	        customer.setAddress("北京市东直门外 "+i+" 号");
	        customer.setPaymentDays(3);
	
	        customer.setPostalCode("邮编"+i);
	        customer.setFax("传真"+i);
	        customer.setDutyParagraph("税号"+i);
	        customer.setInvoiceSendingAddress("发票地址"+i);
	        customer.setInvoiceSendingPcode("发票邮编"+i);
	        customer.setDeliveryAddress("收货地址"+i);
	        customer.setBankAccount("6000 5000");
	        customer.setRegTime("2018-08");
	        customer.setRegCapital("注册资本"+i);
	        customer.setDebtLimit(12.3);
//	        customer.setMobile("123456789000");
//	        customer.setFixedPhone("123456");
	        customer.setWebSite("www.xx"+i);
//	        customer.setCurrencyId(1l);
	        customer.setCustomerInfo("客户信息"+i);
	        customer.setAreaId(7l);
	        customer.setAccountUnitId(137l);
	        customer.setCustomerLevelId(143l);
	        customer.setPaymentTypeId(140l);
	        customers.add(customer);
    	}
    	customerDao.insertByForeachCustomer(customers);
       
    }

    @Test
    public void updateCustomer() {
        Customer customer = customerDao.getCustomerById(2l);
        customer.setCreatedBy(11l);
        CustomerContacts cc = new CustomerContacts();
        cc.setCustomerId(customer.getRowId());
        cc.setFax("fzx");
        cc.setMail("123@qq.com");
        cc.setMobile("12398989898");
        cc.setName("李四2");
        cc.setPosition("经理2");
        cc.setTel("1234567");
        List<CustomerContacts> list = new ArrayList<>();
        list.add(cc);
        list.add(cc);
        customer.setCustomerContactsList(list);
        customerService.update(customer);

    }

    @Test
    public void pageList() {
        customerContactsDao.getContactsByCustomerId(2l);
    }

    @Test
    public void delete() {
        customerService.deleteByIds("1");
    }

    @Test
    public void select() {
//        customerService.getCustomerAndContacts(2l);
//        customerDao.getCurrencyId(7l,132l);
        SaleContractForm form = new SaleContractForm();
        form.setCustomer(5l);
        saleContractService.searchSaleContractByCustomer(form);
    }
    
    @Test
    public void select1() {
//    	Customer customerByName = customerDao.getCustomerByName("客户1");
//    	System.out.println(customerByName);
        List<Long> ll = new ArrayList<>();
        ll.add(5L);
        ll.add(6L);
        ll.add(12L);
        List<Customer> customerByRowIds = customerDao.getCustomerByRowIds(ll);
        System.out.println(customerByRowIds);

    }
    
    @Test
    public void importExcel1() throws Exception{
    	File file = new File("E:\\客户导入模板.xls");
    	FileInputStream is = new FileInputStream(file);
    	List<CustomerExcelForm> list = new ArrayList<CustomerExcelForm>();
    	Boolean isExcel2003 = true;
//		Boolean isExcel2003 = false;
    	ImportExcelUtils.export(is, CustomerExcelForm.class, list, isExcel2003,1,0);
    	String importExcelProduct = customerService.importExcelCustomer(list);
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+importExcelProduct);
    }
    
    @Test
	public void importExcel2() throws Exception{
		File file = new File("E:\\客户导入模板.xls");
		FileInputStream is = new FileInputStream(file);
		List<CustomerContactsExcelForm> list = new ArrayList<CustomerContactsExcelForm>();
		Boolean isExcel2003 = true;
//		Boolean isExcel2003 = false;
		ImportExcelUtils.export(is, CustomerContactsExcelForm.class, list, isExcel2003,1,1);
		String importExcelProduct = customerContactsService.importExcelCustomerContacts(list);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+importExcelProduct);
	}
//    @Test
//    public void importExcel1() throws Exception{
//    	File file = new File("E:\\客户导入模板.xls");
//    	FileInputStream is = new FileInputStream(file);
//    	List<CustomerExcelForm> list = new ArrayList<CustomerExcelForm>();
//    	Boolean isExcel2003 = true;
////		Boolean isExcel2003 = false;
//    	ImportExcelUtils.export2(is, CustomerExcelForm.class, list, isExcel2003,1,0);
//    	String importExcelProduct = customerService.importExcelCustomer(list);
//    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+importExcelProduct);
//    }
//
//    @Test
//	public void importExcel2() throws Exception{
//		File file = new File("E:\\客户导入模板.xls");
//		FileInputStream is = new FileInputStream(file);
//		List<CustomerContactsExcelForm> list = new ArrayList<CustomerContactsExcelForm>();
//		Boolean isExcel2003 = true;
////		Boolean isExcel2003 = false;
//		ImportExcelUtils.export2(is, CustomerContactsExcelForm.class, list, isExcel2003,1,1);
//		String importExcelProduct = customerContactsService.importExcelCustomerContacts(list);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+importExcelProduct);
//	}
}









