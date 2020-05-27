package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;

public class EltSocialSecurityForm extends BaseForm {

    private Long baseId;

    private String ruleName;

    private String staffId;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}
