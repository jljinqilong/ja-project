package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;

public class EltSalaryForm extends BaseForm {

    private Long baseId;

    private String wageCategory;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getWageCategory() {
        return wageCategory;
    }

    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

}
