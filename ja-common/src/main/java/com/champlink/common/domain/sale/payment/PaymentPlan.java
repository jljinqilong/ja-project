package com.champlink.common.domain.sale.payment;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.sale.contract.SaleContract;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/9 10:46
 **/
public class PaymentPlan extends BaseBean {
    /**
     * @Author jsl
     * @Date 合同id 2018/8/9 
     * @Description    
     **/
    private Long saleContractId;
    /**
     * @Author jsl
     * @Date 合同编号 2018/8/9 
     * @Description    
     **/
    private String saleContractNo;
    /**
     * @Author jsl
     * @Date 回款期次 2018/8/9
     * @Description
     **/
    private String period;
    /**
     * @Author jsl
     * @Date 计划付款日期 2018/8/9 
     * @Description    
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date payDate;
    /**
     * @Author jsl
     * @Date 计划付款金额 2018/8/9
     * @Description
     **/
    private BigDecimal payAmount;
    /**
     * @Author jsl
     * @Date 付款方式 2018/8/9 
     * @Description    
     **/
    private Long payMethod;
    /**
     * @Author jsl
     * @Date 备注 2018/8/9
     * @Description    
     **/
    private String remark;
    
    /**
     * @Author jsl
     * @Date 客户id 2018/8/9
     * @Description    
     **/
    private Long customerId;
    /**
     * @Author jsl
     * @Date 客户名称 2018/8/9
     * @Description
     **/
    private String customerName;

    /**
     * @Author jsl
     * @Date 合同标题 2018/8/9
     * @Description
     **/
    private Long contractTitleId;

    /**
     * @Author jsl
     * @Date 逾期天数 2018/8/13
     * @Description
     **/
    private String overDate;

    /**
     * @Author jsl
     * @Date 可计划的金额 2018/8/14
     * @Description
     **/
    private BigDecimal ApplicableAmount;

    /**
     * @Author jsl
     * @Date 计划单已付金额（对于某个回款计划而言） 2018/8/14
     * @Description
     **/
    private BigDecimal plan_paymentAmount;
    /**
     * @Author jsl
     * @Date 计划单未付金额（对于某个回款计划而言） 2018/8/14
     * @Description
     **/
    private BigDecimal plan_unPaymentAmount;

    /**
     * @Author jsl
     * @Date 合同 2018/8/13
     * @Description
     **/
    private SaleContract saleContract;
    
    /**
     * @Author jsl
     * @Date 回款记录list 2018/8/13
     * @Description
     **/
    private List<PaymentPlanDetail> paymentPlanDetailList;


    public BigDecimal getApplicableAmount() {
        return ApplicableAmount;
    }

    public void setApplicableAmount(BigDecimal applicableAmount) {
        ApplicableAmount = applicableAmount;
    }

    public BigDecimal getPlan_paymentAmount() {
        return plan_paymentAmount;
    }

    public void setPlan_paymentAmount(BigDecimal plan_paymentAmount) {
        this.plan_paymentAmount = plan_paymentAmount;
    }

    public BigDecimal getPlan_unPaymentAmount() {
        return plan_unPaymentAmount;
    }

    public void setPlan_unPaymentAmount(BigDecimal plan_unPaymentAmount) {
        this.plan_unPaymentAmount = plan_unPaymentAmount;
    }

    public List<PaymentPlanDetail> getPaymentPlanDetailList() {
        return paymentPlanDetailList;
    }

    public void setPaymentPlanDetailList(List<PaymentPlanDetail> paymentPlanDetailList) {
        this.paymentPlanDetailList = paymentPlanDetailList;
    }

    public SaleContract getSaleContract() {
        return saleContract;
    }

    public void setSaleContract(SaleContract saleContract) {
        this.saleContract = saleContract;
    }

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getContractTitleId() {
        return contractTitleId;
    }

    public void setContractTitleId(Long contractTitleId) {
        this.contractTitleId = contractTitleId;
    }

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public String getSaleContractNo() {
        return saleContractNo;
    }

    public void setSaleContractNo(String saleContractNo) {
        this.saleContractNo = saleContractNo;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Long getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Long payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
