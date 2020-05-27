package com.champlink.sale;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.contract.SaleContractDetail;
import com.champlink.common.domain.sale.contract.SalePayPlan;
import com.champlink.common.form.sale.contract.InvoiceExcelForm;
import com.champlink.common.form.sale.contract.SaleContractDetailExcelForm;
import com.champlink.common.form.sale.contract.SaleContractExcelForm;
import com.champlink.common.form.sale.contract.SaleContractForm;
import com.champlink.common.form.sale.customer.CustomerContactsExcelForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.contract.SaleContractDao;
import com.champlink.sale.service.area.AreaService;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.contract.SaleContractService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleContractTest {

    @Autowired
    private SaleCommonService saleCommonService;

    @Autowired
    private SaleContractDao saleContractDao;
    @Autowired
    private SaleContractService saleContractService;

    @Test
    public void Test() {
//        //增加
//        SaleContract saleContract = new SaleContract();
//
//        saleContract.setOrgId(1L);
//        saleContract.setUserName("admin");
//
//        saleContract.setContractNo("JASALEHT2018090900002");
//        saleContract.setContractDateE(Date.valueOf("2019-08-08"));
//        saleContract.setContractDateS(Date.valueOf("2019-08-08"));
//        saleContract.setClientContractor(14L);
//        saleContract.setContractTitle(149L);
//        saleContract.setContractType(147L);
//        saleContract.setCreatedDate(Date.valueOf("2019-08-08"));
//        saleContract.setCustomer(2L);
//        saleContract.setDepartId(22L);
//        saleContract.setOurSignatory(1L);
//        saleContract.setPaymentAmount(new BigDecimal(30000));
//        saleContract.setPayMethod(139L);
//        saleContract.setRemark("test111");
//        saleContract.setSignDate(Date.valueOf("2019-08-08"));
//        saleContract.setTicketAmount(new BigDecimal(20000));
//        saleContract.setTotalAmount(new BigDecimal(30000));
//        saleContract.setUnPaymentAmount(new BigDecimal(10000));
//        saleContract.setUnTicketAmount(new BigDecimal(10000));
//        //产品list
//        List<SaleContractDetail> saleContractDetailList = new ArrayList<SaleContractDetail>();
//        SaleContractDetail saleContractDetail = new SaleContractDetail();
//        saleContractDetail.setCreatedBy(1L);
//        saleContractDetail.setProductionId(1L);
//        saleContractDetailList.add(saleContractDetail);
//
//        saleContract.setSaleContractDetailList(saleContractDetailList);
//
//        //付款计划
////        List<SalePayPlan> salePayPlanList = new ArrayList<SalePayPlan>();
////        SalePayPlan salePayPlan = new SalePayPlan();
////        salePayPlan.setPayDate(Date.valueOf("2018-08-08"));
////        salePayPlan.setPayAmount(new BigDecimal(1000));
////        salePayPlan.setPayMethod(139L);
////        salePayPlan.setRemark("plan test 123");
////        salePayPlan.setStatus(0);
////        salePayPlanList.add(salePayPlan);
////
////        saleContract.setSalePayPlanList(salePayPlanList);
//
//        saleContractService.add(saleContract);

//        //删除
//        Long rowId = 9L;
//        saleContractService.del(rowId);
//
//
//    }
//
//    @Test
//    public void select(){
//        saleContractService.searchSaleContractByCustomer(2l);
//    }

//     修改
//        SaleContract saleContract = new SaleContract();
//        saleContract.setRowId(10L);
//        saleContract.setContractNo("JASALEHT201809090003");
//        saleContract.setContractDateE(Date.valueOf("2019-08-09"));
//        saleContract.setContractDateS(Date.valueOf("2019-08-09"));
//        saleContract.setClientContractor("铭神");
//        saleContract.setContractTitle("1个亿订单");
//        saleContract.setContractType(147L);
//        saleContract.setCreatedDate(Date.valueOf("2019-08-09"));
//        saleContract.setCustomer(2L);
//        saleContract.setDepartId(22L);
//        saleContract.setOurSignatory("晶奥");
//        saleContract.setPaymentAmount(new BigDecimal(300000));
//        saleContract.setPayMethod(139L);
//        saleContract.setRemark("test update11111");
//        saleContract.setSignDate(Date.valueOf("2019-08-08"));
//        saleContract.setTicketAmount(new BigDecimal(200000));
//        saleContract.setTotalAmount(new BigDecimal(300000));
//        saleContract.setUnPaymentAmount(new BigDecimal(100000));
//        saleContract.setUnTicketAmount(new BigDecimal(100000));
//        saleContract.setNextTime(Date.valueOf("2018-08-09"));
//        //产品list
//        List<SaleContractDetail> saleContractDetailList = new ArrayList<SaleContractDetail>();
//        SaleContractDetail saleContractDetail = new SaleContractDetail();
//        saleContractDetail.setCreatedBy(1L);
//        saleContractDetail.setProductionId(1L);
//        saleContractDetailList.add(saleContractDetail);
//
//        saleContract.setSaleContractDetailList(saleContractDetailList);
//
//        saleContractService.update(saleContract);

        Long rowId = 1L;
        saleContractService.getPaymentDetailBySaleContractId(rowId);

    }
    
    @Test
  	public void importExcelContract() throws Exception{
  		File file = new File("E:\\合同导入模板.xls");
  		FileInputStream is = new FileInputStream(file);
  		List<SaleContractExcelForm> list = new ArrayList<SaleContractExcelForm>();
  		Boolean isExcel2003 = true;
  		ImportExcelUtils.export(is, SaleContractExcelForm.class, list, isExcel2003,1,0);
  		System.out.println(list);
  		saleContractService.importExcelSaleContract(list);
  		
  	}
    
    @Test
  	public void importExcelProductDetail() throws Exception{
  		File file = new File("E:\\合同导入模板.xls");
  		FileInputStream is = new FileInputStream(file);
  		List<SaleContractDetailExcelForm> list = new ArrayList<SaleContractDetailExcelForm>();
  		Boolean isExcel2003 = true;
  		ImportExcelUtils.export(is, SaleContractDetailExcelForm.class, list, isExcel2003,1,1);
  		saleContractService.importExcelSaleContractDetail(list);
  		
  	}
    
    @Test
  	public void importExcelInvoiceForm() throws Exception{
  		File file = new File("E:\\合同导入模板.xls");
  		FileInputStream is = new FileInputStream(file);
  		List<InvoiceExcelForm> list = new ArrayList<InvoiceExcelForm>();
  		Boolean isExcel2003 = true;
  		ImportExcelUtils.export(is, InvoiceExcelForm.class, list, isExcel2003,1,2);
  		saleContractService.importExcelInvoice(list);
  	}
    
    
    
    
    
    
    
    
    
}
