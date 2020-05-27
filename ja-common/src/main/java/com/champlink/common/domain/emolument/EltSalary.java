package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

public class EltSalary extends BaseBean {

    /**
     * 基地
     */
    private Long baseId;

    /**
     * 工资类别
     */
    private String wageCategory;

    /**
     * 工资金额
     */
    private String payTheAmount;

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
     * 获取工资类别
     *
     * @return wage_category - 工资类别
     */
    public String getWageCategory() {
        return wageCategory;
    }

    /**
     * 设置工资类别
     *
     * @param wageCategory 工资类别
     */
    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    /**
     * 获取工资金额
     *
     * @return pay_the_amount - 工资金额
     */
    public String getPayTheAmount() {
        return payTheAmount;
    }

    /**
     * 设置工资金额
     *
     * @param payTheAmount 工资金额
     */
    public void setPayTheAmount(String payTheAmount) {
        this.payTheAmount = payTheAmount;
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