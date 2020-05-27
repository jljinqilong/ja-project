package com.champlink.common.domain.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class Award extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 荣誉名称
     */
    private String awardName;

    /**
     * 奖励类型
     */
    private Long awardType;

    /**
     * 奖励级别
     */
    private Long awardRank;

    /**
     * 获奖时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date awardTime;

    /**
     * 年度（选到年即可）
     */
    @DateTimeFormat(pattern = "yyyy")
    @JSONField(format = "yyyy")
    private Date year;

    /**
     * 批准单位
     */
    private String ratifyUnit;

    /**
     * 奖励事由
     */
    private String awardCause;

    /**
     * 奖励依据
     */
    private String awardGist;

    /**
     * 奖励措施
     */
    private String awardMeasure;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private String file;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date credentialIssueDate;//证书颁发日期

    private String credentialIssueOrg;//证书颁发机构

    private String rewardForm;//奖励形式

    public Date getCredentialIssueDate() {
        return credentialIssueDate;
    }

    public void setCredentialIssueDate(Date credentialIssueDate) {
        this.credentialIssueDate = credentialIssueDate;
    }

    public String getCredentialIssueOrg() {
        return credentialIssueOrg;
    }

    public void setCredentialIssueOrg(String credentialIssueOrg) {
        this.credentialIssueOrg = credentialIssueOrg;
    }

    public String getRewardForm() {
        return rewardForm;
    }

    public void setRewardForm(String rewardForm) {
        this.rewardForm = rewardForm;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取奖励名称
     *
     * @return award_name - 奖励名称
     */
    public String getAwardName() {
        return awardName;
    }

    /**
     * 设置奖励名称
     *
     * @param awardName 奖励名称
     */
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    /**
     * 获取奖励类型
     *
     * @return award_type - 奖励类型
     */
    public Long getAwardType() {
        return awardType;
    }

    /**
     * 设置奖励类型
     *
     * @param awardType 奖励类型
     */
    public void setAwardType(Long awardType) {
        this.awardType = awardType;
    }

    /**
     * 获取奖励级别
     *
     * @return award_rank - 奖励级别
     */
    public Long getAwardRank() {
        return awardRank;
    }

    /**
     * 设置奖励级别
     *
     * @param awardRank 奖励级别
     */
    public void setAwardRank(Long awardRank) {
        this.awardRank = awardRank;
    }

    /**
     * 获取获奖时间
     *
     * @return award_time - 获奖时间
     */
    public Date getAwardTime() {
        return awardTime;
    }

    /**
     * 设置获奖时间
     *
     * @param awardTime 获奖时间
     */
    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    /**
     * 获取年度（选到年即可）
     *
     * @return year - 年度（选到年即可）
     */
    public Date getYear() {
        return year;
    }

    /**
     * 设置年度（选到年即可）
     *
     * @param year 年度（选到年即可）
     */
    public void setYear(Date year) {
        this.year = year;
    }

    /**
     * 获取批准单位
     *
     * @return ratify_unit - 批准单位
     */
    public String getRatifyUnit() {
        return ratifyUnit;
    }

    /**
     * 设置批准单位
     *
     * @param ratifyUnit 批准单位
     */
    public void setRatifyUnit(String ratifyUnit) {
        this.ratifyUnit = ratifyUnit;
    }

    /**
     * 获取奖励事由
     *
     * @return award_cause - 奖励事由
     */
    public String getAwardCause() {
        return awardCause;
    }

    /**
     * 设置奖励事由
     *
     * @param awardCause 奖励事由
     */
    public void setAwardCause(String awardCause) {
        this.awardCause = awardCause;
    }

    /**
     * 获取奖励依据
     *
     * @return award_gist - 奖励依据
     */
    public String getAwardGist() {
        return awardGist;
    }

    /**
     * 设置奖励依据
     *
     * @param awardGist 奖励依据
     */
    public void setAwardGist(String awardGist) {
        this.awardGist = awardGist;
    }

    /**
     * 获取奖励措施
     *
     * @return award_measure - 奖励措施
     */
    public String getAwardMeasure() {
        return awardMeasure;
    }

    /**
     * 设置奖励措施
     *
     * @param awardMeasure 奖励措施
     */
    public void setAwardMeasure(String awardMeasure) {
        this.awardMeasure = awardMeasure;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}