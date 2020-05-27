package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

public class EltAccumulationFund extends BaseBean {

    /**
     * 基地
     */
    private Long baseId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 缴费基数
     */
    private String socialInsuranceBase;

    /**
     * 个人比例
     */
    private String personalProportion;

    /**
     * 个人金额
     */
    private String personalAmount;

    /**
     * 公司比例
     */
    private String companyRatio;

    /**
     * 公司金额
     */
    private String companyAmount;

    /**
     * 个人补充比例
     */
    private String personalSupplementaryRatio;

    /**
     * 个人补充金额
     */
    private String personalSupplementaryAmount;

    /**
     * 公司补充比例
     */
    private String companySupplementaryProportion;

    /**
     * 公司补充金额
     */
    private String companySupplementaryAmount;

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
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取缴费基数
     *
     * @return social_insurance_base - 缴费基数
     */
    public String getSocialInsuranceBase() {
        return socialInsuranceBase;
    }

    /**
     * 设置缴费基数
     *
     * @param socialInsuranceBase 缴费基数
     */
    public void setSocialInsuranceBase(String socialInsuranceBase) {
        this.socialInsuranceBase = socialInsuranceBase;
    }

    /**
     * 获取个人比例
     *
     * @return personal_proportion - 个人比例
     */
    public String getPersonalProportion() {
        return personalProportion;
    }

    /**
     * 设置个人比例
     *
     * @param personalProportion 个人比例
     */
    public void setPersonalProportion(String personalProportion) {
        this.personalProportion = personalProportion;
    }

    /**
     * 获取个人金额
     *
     * @return personal_amount - 个人金额
     */
    public String getPersonalAmount() {
        return personalAmount;
    }

    /**
     * 设置个人金额
     *
     * @param personalAmount 个人金额
     */
    public void setPersonalAmount(String personalAmount) {
        this.personalAmount = personalAmount;
    }

    /**
     * 获取公司比例
     *
     * @return company_ratio - 公司比例
     */
    public String getCompanyRatio() {
        return companyRatio;
    }

    /**
     * 设置公司比例
     *
     * @param companyRatio 公司比例
     */
    public void setCompanyRatio(String companyRatio) {
        this.companyRatio = companyRatio;
    }

    /**
     * 获取公司金额
     *
     * @return company_amount - 公司金额
     */
    public String getCompanyAmount() {
        return companyAmount;
    }

    /**
     * 设置公司金额
     *
     * @param companyAmount 公司金额
     */
    public void setCompanyAmount(String companyAmount) {
        this.companyAmount = companyAmount;
    }

    /**
     * 获取个人补充比例
     *
     * @return personal_supplementary_ratio - 个人补充比例
     */
    public String getPersonalSupplementaryRatio() {
        return personalSupplementaryRatio;
    }

    /**
     * 设置个人补充比例
     *
     * @param personalSupplementaryRatio 个人补充比例
     */
    public void setPersonalSupplementaryRatio(String personalSupplementaryRatio) {
        this.personalSupplementaryRatio = personalSupplementaryRatio;
    }

    /**
     * 获取个人补充金额
     *
     * @return personal_supplementary_amount - 个人补充金额
     */
    public String getPersonalSupplementaryAmount() {
        return personalSupplementaryAmount;
    }

    /**
     * 设置个人补充金额
     *
     * @param personalSupplementaryAmount 个人补充金额
     */
    public void setPersonalSupplementaryAmount(String personalSupplementaryAmount) {
        this.personalSupplementaryAmount = personalSupplementaryAmount;
    }

    /**
     * 获取公司补充比例
     *
     * @return company_supplementary_proportion - 公司补充比例
     */
    public String getCompanySupplementaryProportion() {
        return companySupplementaryProportion;
    }

    /**
     * 设置公司补充比例
     *
     * @param companySupplementaryProportion 公司补充比例
     */
    public void setCompanySupplementaryProportion(String companySupplementaryProportion) {
        this.companySupplementaryProportion = companySupplementaryProportion;
    }

    /**
     * 获取公司补充金额
     *
     * @return company_supplementary_amount - 公司补充金额
     */
    public String getCompanySupplementaryAmount() {
        return companySupplementaryAmount;
    }

    /**
     * 设置公司补充金额
     *
     * @param companySupplementaryAmount 公司补充金额
     */
    public void setCompanySupplementaryAmount(String companySupplementaryAmount) {
        this.companySupplementaryAmount = companySupplementaryAmount;
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