package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

public class EltAllowance extends BaseBean {

    /**
     * 基地
     */
    private Long baseId;

    /**
     * 津贴类别
     */
    private String allowanceCategory;

    /**
     * 金额
     */
    private String money;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 获取基地
     *
     * @return baseId - 基地
     */
    public Long getBaseId() {
        return baseId;
    }

    /**
     * 设置基地
     *
     * @param baseId 基地
     */
    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    /**
     * 获取津贴类别
     *
     * @return allowance_category - 津贴类别
     */
    public String getAllowanceCategory() {
        return allowanceCategory;
    }

    /**
     * 设置津贴类别
     *
     * @param allowanceCategory 津贴类别
     */
    public void setAllowanceCategory(String allowanceCategory) {
        this.allowanceCategory = allowanceCategory;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}