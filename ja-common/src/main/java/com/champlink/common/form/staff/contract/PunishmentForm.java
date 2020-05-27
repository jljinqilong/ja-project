package com.champlink.common.form.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.champlink.common.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PunishmentForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 惩处名称
     */
    private String punishmentName;

    /**
     * 惩处类别
     */
    private String punishmentType;

    /**
     * 受惩处时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date punishmentDate;

    /**
     * 惩处依据
     */
    private String punishmentGist;

    /**
     * 惩处事由
     */
    private String punishmentCause;

    /**
     * 惩处措施
     */
    private String punishmentMeasure;

    /**
     * 惩处期限
     */
    private Integer punishmentDeadline;

    /**
     * 批准单位
     */
    private String ratifyUnit;

    /**
     * 撤销处分时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date revocationPunishmentDate;

    /**
     * 备注
     */
    private String remark;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取惩处名称
     *
     * @return punishment_name - 惩处名称
     */
    public String getPunishmentName() {
        return punishmentName;
    }

    /**
     * 设置惩处名称
     *
     * @param punishmentName 惩处名称
     */
    public void setPunishmentName(String punishmentName) {
        this.punishmentName = punishmentName;
    }

    /**
     * 获取惩处类别
     *
     * @return punishment_type - 惩处类别
     */
    public String getPunishmentType() {
        return punishmentType;
    }

    /**
     * 设置惩处类别
     *
     * @param punishmentType 惩处类别
     */
    public void setPunishmentType(String punishmentType) {
        this.punishmentType = punishmentType;
    }

    /**
     * 获取受惩处时间
     *
     * @return punishment_date - 受惩处时间
     */
    public Date getPunishmentDate() {
        return punishmentDate;
    }

    /**
     * 设置受惩处时间
     *
     * @param punishmentDate 受惩处时间
     */
    public void setPunishmentDate(Date punishmentDate) {
        this.punishmentDate = punishmentDate;
    }

    /**
     * 获取惩处依据
     *
     * @return punishment_gist - 惩处依据
     */
    public String getPunishmentGist() {
        return punishmentGist;
    }

    /**
     * 设置惩处依据
     *
     * @param punishmentGist 惩处依据
     */
    public void setPunishmentGist(String punishmentGist) {
        this.punishmentGist = punishmentGist;
    }

    /**
     * 获取惩处事由
     *
     * @return punishment_cause - 惩处事由
     */
    public String getPunishmentCause() {
        return punishmentCause;
    }

    /**
     * 设置惩处事由
     *
     * @param punishmentCause 惩处事由
     */
    public void setPunishmentCause(String punishmentCause) {
        this.punishmentCause = punishmentCause;
    }

    /**
     * 获取惩处措施
     *
     * @return punishment_measure - 惩处措施
     */
    public String getPunishmentMeasure() {
        return punishmentMeasure;
    }

    /**
     * 设置惩处措施
     *
     * @param punishmentMeasure 惩处措施
     */
    public void setPunishmentMeasure(String punishmentMeasure) {
        this.punishmentMeasure = punishmentMeasure;
    }

    /**
     * 获取惩处期限
     *
     * @return punishment_deadline - 惩处期限
     */
    public Integer getPunishmentDeadline() {
        return punishmentDeadline;
    }

    /**
     * 设置惩处期限
     *
     * @param punishmentDeadline 惩处期限
     */
    public void setPunishmentDeadline(Integer punishmentDeadline) {
        this.punishmentDeadline = punishmentDeadline;
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
     * 获取撤销处分时间
     *
     * @return revocation_punishment_date - 撤销处分时间
     */
    public Date getRevocationPunishmentDate() {
        return revocationPunishmentDate;
    }

    /**
     * 设置撤销处分时间
     *
     * @param revocationPunishmentDate 撤销处分时间
     */
    public void setRevocationPunishmentDate(Date revocationPunishmentDate) {
        this.revocationPunishmentDate = revocationPunishmentDate;
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