package com.champlink.sale;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.domain.sale.inquiry.InquiryRecordDetail;
import com.champlink.common.util.cache.RedisService;
import com.champlink.sale.dao.inquiry.InquiryRecordDao;
import com.champlink.sale.dao.inquiry.InquiryRecordDetailDao;
import com.champlink.sale.service.inquiry.InquiryRecordDetailService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquiryDetailTests extends BaseController{

    @Autowired
    private InquiryRecordDetailService inquiryRecordDetailService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private InquiryRecordDetailDao inquiryRecordDetailDao;

    @Test
    public void select(){


    }

    @Test
    public void add() {
        InquiryRecordDetail i = new InquiryRecordDetail();
        i.setCreatedBy(95l);
        i.setInquiryId(10l);
        i.setInquiryPerson("蟹老板");
        i.setProductId(9l);
        i.setCustomerId(1l);
        i.setPrice(BigDecimal.valueOf(23.1));
        i.setRemark("省略1000字");
        inquiryRecordDetailService.add(i);
    }

    @Test
    public void update(){
        InquiryRecordDetail i = inquiryRecordDetailDao.selectByRowId(1l);
        System.out.println(i.toString());
//        inquiryRecordService.update(i);

    }

    @Test
    public void get(){
        inquiryRecordDetailService.getInquiryRecord(9l);
    }
    @Test
    public void pageList(){

    }

     @Test
     public void delete(){
        String ids = "1,2";
         inquiryRecordDetailService.deleteByIds(ids);

     }


}
