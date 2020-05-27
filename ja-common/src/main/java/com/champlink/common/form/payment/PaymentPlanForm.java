package com.champlink.common.form.payment;

import com.champlink.common.form.BaseForm;


/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/9 9:47
 **/
public class PaymentPlanForm extends BaseForm {

    /**
     * @Author jsl
     * @Date 计划回款日期起 2018/8/9 
     * @Description    
     **/
    private String returnDateq;
    /**
     * @Author jsl
     * @Date 计划回款日期止 2018/8/9 
     * @Description    
     **/
    private String returnDatez;
    /**
     * @Author jsl
     * @Date 部门 2018/8/9 
     * @Description    
     **/
    private Long deptId;
    /**
     * @Author jsl
     * @Date 合同负责人 2018/8/9
     * @Description    
     **/
    private Long contractLeader;
    /**
     * @Author jsl
     * @Date 我方签约人 2018/8/9
     * @Description
     **/
    private String ourSignatory;

    public String getReturnDateq() {
        return returnDateq;
    }

    public void setReturnDateq(String returnDateq) {
        this.returnDateq = returnDateq;
    }

    public String getReturnDatez() {
        return returnDatez;
    }

    public void setReturnDatez(String returnDatez) {
        this.returnDatez = returnDatez;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getContractLeader() {
        return contractLeader;
    }

    public void setContractLeader(Long contractLeader) {
        this.contractLeader = contractLeader;
    }

    public String getOurSignatory() {
        return ourSignatory;
    }

    public void setOurSignatory(String ourSignatory) {
        this.ourSignatory = ourSignatory!=null?ourSignatory.trim():null;
    }
}
