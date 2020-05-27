package com.champlink.common.form.payment;

import com.champlink.common.form.BaseForm;

import java.sql.Date;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/9 9:47
 **/
public class PaymentPlanDetailForm extends BaseForm {

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
     * @Date 回款类型 2018/8/9
     * @Description
     **/
    private Long typeId;
    /**
     * @Author jsl
     * @Date 收款人 2018/8/9
     * @Description
     **/
    private String payeeName;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName!=null?payeeName.trim():null;
    }
}
