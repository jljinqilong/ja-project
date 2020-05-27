package com.champlink.sale;

import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.area.AreaDetail;
import com.champlink.common.domain.sale.contract.InvoiceForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.sale.service.area.AreaService;
import com.champlink.sale.service.contract.SaleContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTest {

    @Autowired
    private SaleContractService saleContractService;
    @Autowired
    private RedisService redisService;

//    @Test
//    //添加
//    public void add() {
//        InvoiceForm invoiceForm = new InvoiceForm();
//        invoiceForm.setSaleContractId(10L);
//        invoiceForm.setCreatedBy(118L);
//        invoiceForm.setInvoiceAmount(new BigDecimal(10000));
//        invoiceForm.setInvoiceContext("第二次付款");
//        invoiceForm.setInvoiceNo("0018081002");
//        invoiceForm.setInvoiceTypeId(151L);
//        invoiceForm.setInvoiceUserName("铭神1");
//        invoiceForm.setKprq(Date.valueOf("2018-08-10"));
//        invoiceForm.setRemark("test2");
//        invoiceForm.setCreatedTime(Date.valueOf("2018-08-10"));
//
//        saleContractService.addInvoice(invoiceForm);
//
//
//    }

//    @Test
//    //修改
//    public void update(){
//        InvoiceForm invoiceForm = new InvoiceForm();
//        invoiceForm.setRowId(2L);
//        invoiceForm.setSaleContractId(10L);
//        invoiceForm.setCreatedBy(118L);
//        invoiceForm.setInvoiceAmount(new BigDecimal(20000));
//        invoiceForm.setInvoiceNo("0018081003");
//        invoiceForm.setInvoiceTypeId(152L);
//        invoiceForm.setInvoiceUserName("铭神1");
//        invoiceForm.setKprq(Date.valueOf("2018-08-10"));
//        invoiceForm.setRemark("update1");
//        invoiceForm.setCreatedTime(Date.valueOf("2018-08-10"));
//
//        saleContractService.updateInvoice(invoiceForm);
//
//    }
    @Test
    public void del(){
        Long rowId = 2L;
        saleContractService.delInvoice(rowId);
    }
}
