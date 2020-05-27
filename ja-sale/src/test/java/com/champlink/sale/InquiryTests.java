package com.champlink.sale;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.method.CodeRules;
import com.champlink.sale.dao.customer.CustomerContactsDao;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.dao.inquiry.InquiriesDao;
import com.champlink.sale.dao.inquiry.InquiriesProductDao;
import com.champlink.sale.dao.inquiry.InquiryRecordDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.customer.CustomerService;
import com.champlink.sale.service.inquiry.InquiriesService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquiryTests extends BaseController {

    @Autowired
    private InquiryRecordService inquiryRecordService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private InquiryRecordDao inquiryRecordDao;
    @Autowired
    private InquiriesDao inquiriesDao;

    @Autowired
    private InquiriesService inquiriesService;
    @Autowired
    private HttpServletResponse response ;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private InquiriesProductDao inquiriesProductDao ;



    @Test
    public void select() {
        Inquiries i = new Inquiries();
        i.setRowId(1L);
        i.setCustomerId(5L);
        List<Inquiries> inquiriesByInquiries = inquiriesDao.getInquiriesByInquiries(i);

        System.out.println(inquiriesByInquiries);

    }

    @Test
    public void add() {
//        Inquiries i = new Inquiries();
        InquiryRecord i = new InquiryRecord();
        i.setCreatedBy(20l);
        i.setCustomerId(1l);
        i.setOrgId(1l);
        i.setProductTypeId(1l);
        i.setTradeModeId(1l);
        i.setSaleAreaId(1l);
        i.setSalesmanId(1l);
        i.setPower("功率");
        i.setBatteryTypeId(155l);
        i.setMainGateNumber(BigDecimal.valueOf(12.34));
        i.setBorderColorId(158l);
        i.setBorderSpecificationId(160l);
        i.setBackboardColorId(161l);
        i.setBackboardMaterialId(164l);
        i.setJunctionBoxId(165l);
        i.setGlass("玻璃");
        i.setEvaId(167l);

        i.setEstimatedSigningDate(new Date());
        i.setOrderTotal(BigDecimal.valueOf(1999));
        i.setSellingPrice(BigDecimal.valueOf(888));
        i.setFirstYearAttenuation("10%");
        i.setPaymentTerm("付款条件");


        i.setBomRequire("阿斯蒂芬");
        i.setProjectAddressId(1l);
        i.setDestinationCountryId(1l);
        i.setSalesman("业务员");
        inquiryRecordService.add(i);
//        inquiriesService.add(i);
    }

    @Test
    public void update() {
        Inquiries i = inquiriesDao.getInquiries(2l);
        System.out.println(i.toString());
        i.setCustomerId(20l);
        inquiriesService.update(i);

    }

    @Test
    public void get() {
        inquiryRecordService.getInquiryRecord(18l);
    }

    @Test
    public void test() {
        //确认询价状态
//        inquiryRecordService.confirmInquiry(18l);
        //转询单
//        inquiryRecordService.turnInquiries(18l);
        //转评审
//        inquiriesService.confirmInquiries(2l);
        //评审确认
//        inquiriesService.confirmInquiriesAppraisal(1l);
        //评审转合同
        inquiriesService.appraisalTransferContract(1l);
    }

    @Test
    public void delete() {
        String ids = "17,18";
        inquiryRecordService.deleteByIds(ids);

    }

    @Test
    public void exportExcel(){
        Inquiries inquiries = new Inquiries();
        inquiries.setRowId(36L);
        List<Inquiries> inquiriesByInquiries = inquiriesDao.getInquiriesByInquiries(inquiries);

        String[] headers = {"询单编号","客户编号","区域编号","产品类型"};
        String[] fields = {"rowId","customerId","orgId","productTypeId"};
        String title = "询单数据";
        String [] reders ={"productTypeId:781-电池,782-组件"};


        /*InquiriesProduct inquiriesProduct = new InquiriesProduct();
        List<InquiriesProduct> inquiriesProductList = inquiriesProductDao.getInquiriesProductList(35L);

        String[] headers1 = {"询单编号","产品编号"};
        String[] fields1 = {"inquiriesId","productNo"};
        String title1 = "询单产品数据";


        List<List<?>> allList = new  ArrayList<List<?>>();
        allList.add(inquiriesByInquiries);
        allList.add(inquiriesProductList);

        List<String> titlelList = new ArrayList<>();
        titlelList.add(title);
        titlelList.add(title1);

        List<String[]> strHeaders = new ArrayList<String[]>();
        strHeaders.add(headers);
        strHeaders.add(headers1);


        List<String[]> strFields = new ArrayList<String[]>();
        strFields.add(fields);
        strFields.add(fields1);*/




        ExportExcelUtil.exportExcel(response,inquiriesByInquiries,title,headers,fields,reders);
//        ExportExcelUtil.exportExcelMultipleSheet(response,allList,"询单",titlelList,strHeaders,strFields,null);



    }

    /**
     * 测试询单导出
     */
    @Test
    public void test3(){
        Inquiries inquiries = new Inquiries();
        inquiries.setRowId(36L);
        inquiriesService.exportExcel(request,response,inquiries);
    }


}