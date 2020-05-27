package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

public class EltRefStaffEmolument extends BaseBean {

    /**
     * 员工id
     */
    private Long staffId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 规则类型
     */
    private String type;

    public EltRefStaffEmolument(Long ruleId, Long staffId, String type) {
        super();
        this.ruleId = ruleId;
        this.staffId = staffId;
        this.type = type;
    }

    /**
     * 获取员工id
     *
     * @return staff_id - 员工id
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置员工id
     *
     * @param staffId 员工id
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}