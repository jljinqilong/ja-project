package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;

public class EltSubsidyForm extends BaseForm {

    private Long baseId;

    private String subsidyType;

    private Double subsidies;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getSubsidyType() {
        return subsidyType;
    }

    public void setSubsidyType(String subsidyType) {
        this.subsidyType = subsidyType;
    }

    public Double getSubsidies() {
        return subsidies;
    }

    public void setSubsidies(Double subsidies) {
        this.subsidies = subsidies;
    }

}
