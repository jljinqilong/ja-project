package com.champlink.common.domain.sale.payment;

import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/9 10:49
 **/
public class PaymentPlanDetail extends BaseBean {
    /**
     * @Author jsl
     * @Date 回款计划单id 2018/8/9 
     * @Description    
     **/
    private Long payPlanId;
    /**
     * @Author jsl
     * @Date 回款金额 2018/8/9 
     * @Description    
     **/
    private BigDecimal amount;
    /*
     * @Author jsl
     * @Date 回款期次 2018/8/9 
     * @Description    
     **/
    private String period;
    /**
     * @Author jsl
     * @Date 付款方式 2018/8/9 
     * @Description    
     **/
    private Long payMethod;
    /**
     * @Author jsl
     * @Date 付款类型 2018/8/9 
     * @Description    
     **/
    private Long typeId;
    /**
     * @Author jsl
     * @Date 收款人id 2018/8/9
     * @Description    
     **/
    private Long payee;
    /**
     * @Author jsl
     * @Date 收款人姓名 2018/8/9
     * @Description
     **/
    private String payeeName;
    /**
     * @Author jsl
     * @Date 客户名 2018/8/9
     * @Description    
     **/
    private String customerName;
    
    /**
     * @Author jsl
     * @Date 合同id 2018/8/9
     * @Description    
     **/
    private Long saleContractId;

    /**
     * @Author jsl
     * @Date 合同标题 2018/8/9
     * @Description
     **/
    private String contractTitle;

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public Long getPayPlanId() {
        return payPlanId;
    }

    public void setPayPlanId(Long payPlanId) {
        this.payPlanId = payPlanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Long payMethod) {
        this.payMethod = payMethod;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }
}
