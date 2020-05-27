package com.champlink.sale;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.sale.inquiry.InquiriesDeliveryTime;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.util.cache.RedisService;
import com.champlink.sale.dao.inquiry.InquiriesDao;
import com.champlink.sale.dao.inquiry.InquiriesDeliveryTimeDao;
import com.champlink.sale.dao.inquiry.InquiriesProductDao;
import com.champlink.sale.dao.inquiry.InquiryRecordDao;
import com.champlink.sale.service.inquiry.InquiriesService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquiriesTest extends BaseController {

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
    private InquiriesProductDao inquiriesProductDao;
    @Autowired
    private InquiriesDeliveryTimeDao inquiriesDeliveryTimeDao;

    @Test
    public void select() {


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
        List<InquiriesDeliveryTime> inquiriesDeliveryTime = inquiriesDeliveryTimeDao.getInquiriesDeliveryTimeList(1l);
        for (InquiriesDeliveryTime i : inquiriesDeliveryTime) {
            System.out.println(">>>>: " + i.getRowId());
        }
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
//        inquiriesService.appraisalTransferContract(1l);

        List<InquiriesProduct> list = new ArrayList<>();
        InquiriesProduct i = new InquiriesProduct();
        i.setProductNo("123213");
        list.add(i);
        inquiriesProductDao.insertProductBatch(list);
    }

    @Test
    public void delete() {
        String ids = "17,18";
        inquiryRecordService.deleteByIds(ids);

    }


}
