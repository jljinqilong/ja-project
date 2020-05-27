package com.champlink.common.domain.emolument.ExcelBean;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;
import com.champlink.common.util.annotations.Validation;

public class EltAccumulationFundExcelBean extends BaseForm{


    private Long baseId;
    /**
     * 基地
     */
    @Excel(title = "基地", isNull = false)
    private String base;

    /**
     * 规则名称
     */
    @Excel(title = "规则名称", isNull = false)
    private String ruleName;

    /**
     * 缴费基数
     */
    @Excel(title = "缴费基数", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "缴费基数格式错误")
    private String socialInsuranceBase;

    /**
     * 个人比例
     */
    @Excel(title = "个人比例", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "个人比例格式错误")
    private String personalProportion;

    /**
     * 个人金额
     */
    @Excel(title = "个人金额", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "个人金额格式错误")
    private String personalAmount;

    /**
     * 公司比例
     */
    @Excel(title = "公司比例", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "公司比例格式错误")
    private String companyRatio;

    /**
     * 公司金额
     */
    @Excel(title = "公司金额", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "公司金额格式错误")
    private String companyAmount;

    /**
     * 个人补充比例
     */
    @Excel(title = "个人补充比例", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "个人补充比例格式错误")
    private String personalSupplementaryRatio;

    /**
     * 个人补充金额
     */
    @Excel(title = "个人补充金额", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "个人补充金额格式错误")
    private String personalSupplementaryAmount;

    /**
     * 公司补充比例
     */
    @Excel(title = "公司补充比例", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "公司补充比例格式错误")
    private String companySupplementaryProportion;

    /**
     * 公司补充金额
     */
    @Excel(title = "公司补充金额", isNull = false)
    @Validation(rule = "^[0-9]+([.]{1}[0-9]+){0,1}$", msg = "公司补充金额格式错误")
    private String companySupplementaryAmount;

    /**
     * 备注
     */
    @Excel(title = "备注", isNull = false)
    private String remarks;

    /**
     * 错误描述
     */
    private String errMsg;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getSocialInsuranceBase() {
        return socialInsuranceBase;
    }

    public void setSocialInsuranceBase(String socialInsuranceBase) {
        this.socialInsuranceBase = socialInsuranceBase;
    }

    public String getPersonalProportion() {
        return personalProportion;
    }

    public void setPersonalProportion(String personalProportion) {
        this.personalProportion = personalProportion;
    }

    public String getPersonalAmount() {
        return personalAmount;
    }

    public void setPersonalAmount(String personalAmount) {
        this.personalAmount = personalAmount;
    }

    public String getCompanyRatio() {
        return companyRatio;
    }

    public void setCompanyRatio(String companyRatio) {
        this.companyRatio = companyRatio;
    }

    public String getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(String companyAmount) {
        this.companyAmount = companyAmount;
    }

    public String getPersonalSupplementaryRatio() {
        return personalSupplementaryRatio;
    }

    public void setPersonalSupplementaryRatio(String personalSupplementaryRatio) {
        this.personalSupplementaryRatio = personalSupplementaryRatio;
    }

    public String getPersonalSupplementaryAmount() {
        return personalSupplementaryAmount;
    }

    public void setPersonalSupplementaryAmount(String personalSupplementaryAmount) {
        this.personalSupplementaryAmount = personalSupplementaryAmount;
    }

    public String getCompanySupplementaryProportion() {
        return companySupplementaryProportion;
    }

    public void setCompanySupplementaryProportion(String companySupplementaryProportion) {
        this.companySupplementaryProportion = companySupplementaryProportion;
    }

    public String getCompanySupplementaryAmount() {
        return companySupplementaryAmount;
    }

    public void setCompanySupplementaryAmount(String companySupplementaryAmount) {
        this.companySupplementaryAmount = companySupplementaryAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
