package com.champlink.sale;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.sale.product.Product;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.sale.product.ImportProductForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.sale.controller.product.ProductController;
import com.champlink.sale.dao.product.ProductDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	@Autowired
	private ProductDao productDao;
	
	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleCommonService saleCommonService;
	
	@Autowired
    private ProductController productController;

    private MockMvc mockMvc;
    
    

	
	@Test
	public void testQuery(){
		Product p = new Product();
		p.setBomId(1L);
		p.setBomNo("JA180807001");
		p.setProductNo("JAM60S01-270/PR");
		List<Product> productByProduct = productDao.getProductByProduct(p);
		System.out.println(productByProduct);
		
	}
	
	@Test
	public void testCheckProductNoIsExist(){
		List<String> list = new ArrayList<String>();
		list.add("TUV-301-MA");
		list.add("TUV-305-MA");
		list.add("TUV-306-MA");
		List<Product> checkProductNoIsExist = productDao.checkProductNoIsExist(list);
		System.out.println(checkProductNoIsExist);
	}
	
	@Test
	public void testInsert(){
		Product p = new Product();
		p.setBomId(7L);
		p.setBomNo("JA180807002");
		p.setProductNo("TUV-307-MA");
		
		p.setCreatedBy(7777L);
		p.setStatus(0);
		p.setDelFlag(0);
		productDao.insertProduct(p);
		System.out.println();
	}
	
	@Test
	public void testupdate(){
		Product p = new Product();
		p.setBomId(4L);
		p.setBomNo("JA180807004");
		p.setProductNo("TUV-301-MA");
		
		productDao.updateProductByRowId(p);
		System.out.println();
	}
	
	@Test
	public void testinsertBatch(){
		List<Product> list = new ArrayList<Product>();
		Product p = new Product();
		p.setBomId(null);
		p.setBomNo(null);
		p.setProductNo("sssssss");
		p.setCellNumberId(111L);
		p.setCellTechnologyId(111L);
		p.setMuduleCodeId(111L);
		p.setMuduleTypeId(111L);
		p.setSiliconTypeId(111L);
		p.setRatedPower("20");
		p.setStatus(0);
		p.setDelFlag(0);
		
		Product p1 = new Product();
		p1.setBomId(null);
		p1.setBomNo(null);
		p1.setProductNo("sssssss");
		p1.setCellNumberId(222L);
		p1.setCellTechnologyId(222L);
		p1.setMuduleCodeId(222L);
		p1.setMuduleTypeId(222L);
		p1.setSiliconTypeId(222L);
		p1.setRatedPower("20");
		p1.setStatus(0);
		p1.setDelFlag(0);
		
		list.add(p);
		list.add(p1);
		
		productDao.insertByForeachProdect(list);
	}
	
	@Test
	public void testServiceUpdate(){
		Product p = new Product();
		p.setRowId(4L);
		p.setBomId(4L);
		p.setBomNo("JA180807004");
		p.setProductNo("TUV-301-MA");
		
		p.setStatus(1);
		p.setDelFlag(1);
		productService.updateProductByRowId(p);
		System.out.println();
	}
	
	
	@Test
	public void testCode(){
		String byTpye = saleCommonService.getByTpye("SILICON_TYPE");
		
		JSONObject parseObject = JSONObject.parseObject(byTpye);
        List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
		
		
		System.out.println(allCodeList);
	}
	
	
	
	
	
	/*@Before
	public void setUp() throws Exception {
		 mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}*/
	
	/**
	 * 测试controller
	 * @throws Exception
	 */
	@Test
	public void testcc() throws Exception {
		 MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/product/get/1")).andReturn().getResponse();
		 System.out.println(response.getContentAsString());
	}
	
	@Test
	public void importExcel() throws Exception{
		File file = new File("E:\\产品导入模板.xls");
		FileInputStream is = new FileInputStream(file);
		List<ImportProductForm> list = new ArrayList<ImportProductForm>();
		Boolean isExcel2003 = true;
//		Boolean isExcel2003 = false;
		ImportExcelUtils.export(is, ImportProductForm.class, list, isExcel2003,1,0);
		String importExcelProduct = productService.importExcelProduct(list);
		System.out.println(importExcelProduct);
	}
//	@Test
//	public void importExcel() throws Exception{
//		File file = new File("E:\\产品导入模板.xls");
//		FileInputStream is = new FileInputStream(file);
//		List<ImportProductForm> list = new ArrayList<ImportProductForm>();
//		Boolean isExcel2003 = true;
////		Boolean isExcel2003 = false;
//		ImportExcelUtils.export2(is, ImportProductForm.class, list, isExcel2003,1,0);
//		String importExcelProduct = productService.importExcelProduct(list);
//		System.out.println(importExcelProduct);
//	}

}















