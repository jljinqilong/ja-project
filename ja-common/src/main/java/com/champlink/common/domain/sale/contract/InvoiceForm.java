package com.champlink.common.domain.sale.contract;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/6 20:02
 **/
public class InvoiceForm extends BaseBean {

    /**
     * @Author jsl
     * @Date 销售单id 2018/8/6
     * @Description
     **/
    private Long saleContractId;
    /**
     * @Author jsl
     * @Date 开票日期 2018/8/6
     * @Description    
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date kprq;

    /**
     * @Author jsl
     * @Date 票据内容 2018/8/6
     * @Description
     **/
    private String invoiceContext;
    /**
     * @Author jsl
     * @Date 发票金额 2018/8/6 
     * @Description    
     **/
    private BigDecimal invoiceAmount;
    
    /**
     * @Author jsl
     * @Date 票据类型 2018/8/6
     * @Description    
     **/
    private Long invoiceTypeId;
    
    /**
     * @Author jsl
     * @Date 票据类型名称 2018/8/6 
     * @Description    
     **/
    private String invoiceTypeName;
    
    /**
     * @Author jsl
     * @Date 发票号码 2018/8/6
     * @Description    
     **/
    private String invoiceNo;

    /**
     * @Author jsl
     * @Date 经手人 2018/8/6
     * @Description
     **/
    private Long invoiceUserId;

    /**
     * @Author jsl
     * @Date 经手人名称 2018/8/6
     * @Description
     **/
    private String invoiceUserName;
    
    /**
     * @Author jsl
     * @Date 备注 2018/8/6
     * @Description    
     **/
    private String remark;

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public Date getKprq() {
        return kprq;
    }

    public void setKprq(Date kprq) {
        this.kprq = kprq;
    }

    public String getInvoiceContext() {
        return invoiceContext;
    }

    public void setInvoiceContext(String invoiceContext) {
        this.invoiceContext = invoiceContext;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Long getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(Long invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Long getInvoiceUserId() {
        return invoiceUserId;
    }

    public void setInvoiceUserId(Long invoiceUserId) {
        this.invoiceUserId = invoiceUserId;
    }

    public String getInvoiceUserName() {
        return invoiceUserName;
    }

    public void setInvoiceUserName(String invoiceUserName) {
        this.invoiceUserName = invoiceUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
