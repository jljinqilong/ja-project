package com.champlink.common.domain.sale.contract;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/6 16:18
 **/
public class SalePayPlan extends BaseBean {
    /**
     * @Author jsl
     * @Date 销售合同id 2018/8/6
     * @Description
     **/
    private Long saleContractId;

    /**
     * @Author jsl
     * @Date 销售合同编号 2018/8/8
     * @Description
     **/
    private String saleContractNo;
    /**
     * @Author jsl
     * @Date 计划付款日期 2018/8/6 
     * @Description    
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date payDate;
    
    /**
     * @Author jsl
     * @Date 计划付款金额 2018/8/6
     * @Description    
     **/
    @NotBlank(message = "付款金额不能为空")
    private BigDecimal payAmount;

    /**
     * @Author jsl
     * @Date 付款方式 2018/8/6
     * @Description
     **/
    @NotBlank(message = "付款方式不能为空")
    private Long payMethod;

    /**
     * @Author jsl
     * @Date 备注 2018/8/6
     * @Description
     **/
    private String remark;

    /**
     * @Author jsl
     * @Date 回款计划明细 2018/8/8
     * @Description
     **/
    private List<SalePayPlanDetail> salePayPlanDetailList;


    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
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

    public String getSaleContractNo() {
        return saleContractNo;
    }

    public void setSaleContractNo(String saleContractNo) {
        this.saleContractNo = saleContractNo;
    }

    public List<SalePayPlanDetail> getSalePayPlanDetailList() {
        return salePayPlanDetailList;
    }

    public void setSalePayPlanDetailList(List<SalePayPlanDetail> salePayPlanDetailList) {
        this.salePayPlanDetailList = salePayPlanDetailList;
    }
}
