package com.champlink.common.form.area;

import com.champlink.common.form.BaseForm;

public class AreaForm extends BaseForm {

    /**
     * rowId
     */
    private Long rowId;

    /**
     * 区域名称
     */
    private String regionName;

    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName != null ? regionName.trim() : null;
    }
}
