package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

/**
 * 补贴规则
 * @author Administrator
 *
 */
public class EltSubsidy extends BaseBean {

    /**
     * 基地
     */
    private Long baseId;

    /**
     * 补贴类型
     */
    private String subsidyType;

    /**
     * 补贴金额
     */
    private String subsidies;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 获取基地
     *
     * @return base - 基地
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
     * 获取补贴类型
     *
     * @return subsidy_type - 补贴类型
     */
    public String getSubsidyType() {
        return subsidyType;
    }

    /**
     * 设置补贴类型
     *
     * @param subsidyType 补贴类型
     */
    public void setSubsidyType(String subsidyType) {
        this.subsidyType = subsidyType;
    }

    /**
     * 获取补贴金额
     *
     * @return subsidies - 补贴金额
     */
    public String getSubsidies() {
        return subsidies;
    }

    /**
     * 设置补贴金额
     *
     * @param subsidies 补贴金额
     */
    public void setSubsidies(String subsidies) {
        this.subsidies = subsidies;
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