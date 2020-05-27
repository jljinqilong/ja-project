package com.champlink.common.form.sale.stock;

import com.champlink.common.form.BaseForm;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/14 17:41
 **/
public class StockForm extends BaseForm {
    /**
     * @Author jsl
     * @Date 客户 2018/8/14 
     * @Description    
     **/
    private Long customer;
    /**
     * @Author jsl
     * @Date 备货地点 2018/8/14
     * @Description    
     **/
    private Long stockAddress;

    /**
     * @Author jsl
     * @Date 备货编号 2018/8/14 
     * @Description    
     **/
    private String stockNo;

    /**
     * @Author jsl
     * @Date 其他 2018/8/14
     * @Description
     **/
    private String otherMessage;

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getStockAddress() {
        return stockAddress;
    }

    public void setStockAddress(Long stockAddress) {
        this.stockAddress = stockAddress;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getOtherMessage() {
        return otherMessage;
    }

    public void setOtherMessage(String otherMessage) {
        this.otherMessage = otherMessage;
    }
}
