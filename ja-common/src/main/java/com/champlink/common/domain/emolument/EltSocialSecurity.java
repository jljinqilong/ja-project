package com.champlink.common.domain.emolument;

import com.champlink.common.domain.BaseBean;

public class EltSocialSecurity extends BaseBean {

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
     * 个人养老比例
     */
    private String iindividualPersonRatio;

    /**
     * 个人养老金额
     */
    private String personalPensionAmount;

    /**
     * 个人医疗比例
     */
    private String personalMedicalRatio;

    /**
     * 个人医疗金额
     */
    private String personalMedicalAmount;

    /**
     * 个人工伤比例
     */
    private String personalInjuryRatio;

    /**
     * 个人工伤金额
     */
    private String personalInjuryAmount;

    /**
     * 个人失业比例
     */
    private String personalUnemploymentRatio;

    /**
     * 个人失业金额
     */
    private String personalUnemploymentAmount;

    /**
     * 个人生育比例
     */
    private String personalFertilityRatio;

    /**
     * 个人生育金额
     */
    private String personalFertilityAmount;

    /**
     * 公司养老比例
     */
    private String companyPensionRatio;

    /**
     * 公司养老金额
     */
    private String companyPensionAmount;

    /**
     * 公司医疗比例
     */
    private String companyMedicalRatio;

    /**
     * 公司医疗金额
     */
    private String companyMedicalAmount;

    /**
     * 公司工伤比例
     */
    private String companyInjuryRatio;

    /**
     * 公司工伤金额
     */
    private String companyInjuryAmount;

    /**
     * 公司失业比例
     */
    private String companyUnemploymentRatio;

    /**
     * 公司失业金额
     */
    private String companyUnemploymentAmount;

    /**
     * 公司生育比例
     */
    private String companyBirthRatio;

    /**
     * 公司生育金额
     */
    private String companyBirthAmount;

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
     * 获取个人养老比例
     *
     * @return iindividual_person_ratio - 个人养老比例
     */
    public String getIindividualPersonRatio() {
        return iindividualPersonRatio;
    }

    /**
     * 设置个人养老比例
     *
     * @param iindividualPersonRatio 个人养老比例
     */
    public void setIindividualPersonRatio(String iindividualPersonRatio) {
        this.iindividualPersonRatio = iindividualPersonRatio;
    }

    /**
     * 获取个人养老金额
     *
     * @return personal_pension_amount - 个人养老金额
     */
    public String getPersonalPensionAmount() {
        return personalPensionAmount;
    }

    /**
     * 设置个人养老金额
     *
     * @param personalPensionAmount 个人养老金额
     */
    public void setPersonalPensionAmount(String personalPensionAmount) {
        this.personalPensionAmount = personalPensionAmount;
    }

    /**
     * 获取个人医疗比例
     *
     * @return personal_medical_ratio - 个人医疗比例
     */
    public String getPersonalMedicalRatio() {
        return personalMedicalRatio;
    }

    /**
     * 设置个人医疗比例
     *
     * @param personalMedicalRatio 个人医疗比例
     */
    public void setPersonalMedicalRatio(String personalMedicalRatio) {
        this.personalMedicalRatio = personalMedicalRatio;
    }

    /**
     * 获取个人医疗金额
     *
     * @return personal_medical_amount - 个人医疗金额
     */
    public String getPersonalMedicalAmount() {
        return personalMedicalAmount;
    }

    /**
     * 设置个人医疗金额
     *
     * @param personalMedicalAmount 个人医疗金额
     */
    public void setPersonalMedicalAmount(String personalMedicalAmount) {
        this.personalMedicalAmount = personalMedicalAmount;
    }

    /**
     * 获取个人工伤比例
     *
     * @return personal_injury_ratio - 个人工伤比例
     */
    public String getPersonalInjuryRatio() {
        return personalInjuryRatio;
    }

    /**
     * 设置个人工伤比例
     *
     * @param personalInjuryRatio 个人工伤比例
     */
    public void setPersonalInjuryRatio(String personalInjuryRatio) {
        this.personalInjuryRatio = personalInjuryRatio;
    }

    /**
     * 获取个人工伤金额
     *
     * @return personal_injury_amount - 个人工伤金额
     */
    public String getPersonalInjuryAmount() {
        return personalInjuryAmount;
    }

    /**
     * 设置个人工伤金额
     *
     * @param personalInjuryAmount 个人工伤金额
     */
    public void setPersonalInjuryAmount(String personalInjuryAmount) {
        this.personalInjuryAmount = personalInjuryAmount;
    }

    /**
     * 获取个人失业比例
     *
     * @return personal_unemployment_ratio - 个人失业比例
     */
    public String getPersonalUnemploymentRatio() {
        return personalUnemploymentRatio;
    }

    /**
     * 设置个人失业比例
     *
     * @param personalUnemploymentRatio 个人失业比例
     */
    public void setPersonalUnemploymentRatio(String personalUnemploymentRatio) {
        this.personalUnemploymentRatio = personalUnemploymentRatio;
    }

    /**
     * 获取个人失业金额
     *
     * @return personal_unemployment_amount - 个人失业金额
     */
    public String getPersonalUnemploymentAmount() {
        return personalUnemploymentAmount;
    }

    /**
     * 设置个人失业金额
     *
     * @param personalUnemploymentAmount 个人失业金额
     */
    public void setPersonalUnemploymentAmount(String personalUnemploymentAmount) {
        this.personalUnemploymentAmount = personalUnemploymentAmount;
    }

    /**
     * 获取个人生育比例
     *
     * @return personal_fertility_ratio - 个人生育比例
     */
    public String getPersonalFertilityRatio() {
        return personalFertilityRatio;
    }

    /**
     * 设置个人生育比例
     *
     * @param personalFertilityRatio 个人生育比例
     */
    public void setPersonalFertilityRatio(String personalFertilityRatio) {
        this.personalFertilityRatio = personalFertilityRatio;
    }

    /**
     * 获取个人生育金额
     *
     * @return personal_fertility_amount - 个人生育金额
     */
    public String getPersonalFertilityAmount() {
        return personalFertilityAmount;
    }

    /**
     * 设置个人生育金额
     *
     * @param personalFertilityAmount 个人生育金额
     */
    public void setPersonalFertilityAmount(String personalFertilityAmount) {
        this.personalFertilityAmount = personalFertilityAmount;
    }

    /**
     * 获取公司养老比例
     *
     * @return company_pension_ratio - 公司养老比例
     */
    public String getCompanyPensionRatio() {
        return companyPensionRatio;
    }

    /**
     * 设置公司养老比例
     *
     * @param companyPensionRatio 公司养老比例
     */
    public void setCompanyPensionRatio(String companyPensionRatio) {
        this.companyPensionRatio = companyPensionRatio;
    }

    /**
     * 获取公司养老金额
     *
     * @return company_pension_amount - 公司养老金额
     */
    public String getCompanyPensionAmount() {
        return companyPensionAmount;
    }

    /**
     * 设置公司养老金额
     *
     * @param companyPensionAmount 公司养老金额
     */
    public void setCompanyPensionAmount(String companyPensionAmount) {
        this.companyPensionAmount = companyPensionAmount;
    }

    /**
     * 获取公司医疗比例
     *
     * @return company_medical_ratio - 公司医疗比例
     */
    public String getCompanyMedicalRatio() {
        return companyMedicalRatio;
    }

    /**
     * 设置公司医疗比例
     *
     * @param companyMedicalRatio 公司医疗比例
     */
    public void setCompanyMedicalRatio(String companyMedicalRatio) {
        this.companyMedicalRatio = companyMedicalRatio;
    }

    /**
     * 获取公司医疗金额
     *
     * @return company_medical_amount - 公司医疗金额
     */
    public String getCompanyMedicalAmount() {
        return companyMedicalAmount;
    }

    /**
     * 设置公司医疗金额
     *
     * @param companyMedicalAmount 公司医疗金额
     */
    public void setCompanyMedicalAmount(String companyMedicalAmount) {
        this.companyMedicalAmount = companyMedicalAmount;
    }

    /**
     * 获取公司工伤比例
     *
     * @return company_injury_ratio - 公司工伤比例
     */
    public String getCompanyInjuryRatio() {
        return companyInjuryRatio;
    }

    /**
     * 设置公司工伤比例
     *
     * @param companyInjuryRatio 公司工伤比例
     */
    public void setCompanyInjuryRatio(String companyInjuryRatio) {
        this.companyInjuryRatio = companyInjuryRatio;
    }

    /**
     * 获取公司工伤金额
     *
     * @return company_injury_amount - 公司工伤金额
     */
    public String getCompanyInjuryAmount() {
        return companyInjuryAmount;
    }

    /**
     * 设置公司工伤金额
     *
     * @param companyInjuryAmount 公司工伤金额
     */
    public void setCompanyInjuryAmount(String companyInjuryAmount) {
        this.companyInjuryAmount = companyInjuryAmount;
    }

    /**
     * 获取公司失业比例
     *
     * @return company_unemployment_ratio - 公司失业比例
     */
    public String getCompanyUnemploymentRatio() {
        return companyUnemploymentRatio;
    }

    /**
     * 设置公司失业比例
     *
     * @param companyUnemploymentRatio 公司失业比例
     */
    public void setCompanyUnemploymentRatio(String companyUnemploymentRatio) {
        this.companyUnemploymentRatio = companyUnemploymentRatio;
    }

    /**
     * 获取公司失业金额
     *
     * @return company_unemployment_amount - 公司失业金额
     */
    public String getCompanyUnemploymentAmount() {
        return companyUnemploymentAmount;
    }

    /**
     * 设置公司失业金额
     *
     * @param companyUnemploymentAmount 公司失业金额
     */
    public void setCompanyUnemploymentAmount(String companyUnemploymentAmount) {
        this.companyUnemploymentAmount = companyUnemploymentAmount;
    }

    /**
     * 获取公司生育比例
     *
     * @return company_birth_ratio - 公司生育比例
     */
    public String getCompanyBirthRatio() {
        return companyBirthRatio;
    }

    /**
     * 设置公司生育比例
     *
     * @param companyBirthRatio 公司生育比例
     */
    public void setCompanyBirthRatio(String companyBirthRatio) {
        this.companyBirthRatio = companyBirthRatio;
    }

    /**
     * 获取公司生育金额
     *
     * @return company_birth_amount - 公司生育金额
     */
    public String getCompanyBirthAmount() {
        return companyBirthAmount;
    }

    /**
     * 设置公司生育金额
     *
     * @param companyBirthAmount 公司生育金额
     */
    public void setCompanyBirthAmount(String companyBirthAmount) {
        this.companyBirthAmount = companyBirthAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}