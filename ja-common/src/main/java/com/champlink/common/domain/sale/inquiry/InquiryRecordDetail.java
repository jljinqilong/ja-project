package com.champlink.common.domain.sale.inquiry;

import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;

/**
 * 询价记录明细list
 *
 * @Author created by barrett in 18-8-8 上午9:37
 * @Param
 * @return
 */
public class InquiryRecordDetail extends BaseBean {


    //询价表id
    private Long inquiryId;

    //询价人
    private String inquiryPerson;

    //产品id
    private Long productId;
    //产品编号
    private String productNo;
    //产品名称
    private String productName;

    //客户id
    private Long customerId;
    private String customerName;

    //价格
    private BigDecimal price;

    //备注
    private String remark;

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getInquiryPerson() {
        return inquiryPerson;
    }

    public void setInquiryPerson(String inquiryPerson) {
        this.inquiryPerson = inquiryPerson;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}