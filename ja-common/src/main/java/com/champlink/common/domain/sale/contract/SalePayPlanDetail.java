package com.champlink.common.domain.sale.contract;

import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/8 17:01
 **/
public class SalePayPlanDetail extends BaseBean {

    /**
     * @Author jsl
     * @Date 回款计划id 2018/8/8 
     * @Description    
     **/
    private Long payPlanId;
    /**
     * @Author jsl
     * @Date 回款金额 2018/8/8 
     * @Description    
     **/
    private BigDecimal amount;
    /**
     * @Author jsl
     * @Date 回款期次 2018/8/8 
     * @Description    
     **/
    private int period;
    /**
     * @Author jsl
     * @Date 付款方式id 2018/8/8 
     * @Description    
     **/
    private Long payMethod;
    /**
     * @Author jsl
     * @Date 付款方式 2018/8/8 
     * @Description    
     **/
    private String payMethodName;
    /**
     * @Author jsl
     * @Date 回款类型id 2018/8/8 
     * @Description    
     **/
    private Long typeId;
    /**
     * @Author jsl
     * @Date 回款类型名称 2018/8/8 
     * @Description    
     **/
    private String typeName;
    /**
     * @Author jsl
     * @Date 收款人id 2018/8/8 
     * @Description    
     **/
    private Long payee;
    /**
     * @Author jsl
     * @Date 收款人名称 2018/8/8
     * @Description    
     **/
    private Long payeeName;

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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }



    public String getPayMethodName() {
        return payMethodName;
    }

    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public Long getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(Long payeeName) {
        this.payeeName = payeeName;
    }
}
