package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;

public class EltAllowanceForm extends BaseForm {
    private Long baseId;

    private String allowanceCategory;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getAllowanceCategory() {
        return allowanceCategory;
    }

    public void setAllowanceCategory(String allowanceCategory) {
        this.allowanceCategory = allowanceCategory;
    }

}
