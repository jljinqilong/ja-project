package com.champlink.common.form.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DisabilityForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 残疾类别
     */
    private String disabilityType;

    /**
     * 残疾等级
     */
    private String disabilityRank;

    /**
     * 残疾证号
     */
    private String disabilityNo;

    /**
     * 残疾证发放日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date disabilityCardDate;

    /**
     * 证件有效期开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date validityCertificateStart;

    /**
     * 证件有效期结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date validityCertificateEnd;

    private String remark;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取残疾类别
     *
     * @return disability_type - 残疾类别
     */
    public String getDisabilityType() {
        return disabilityType;
    }

    /**
     * 设置残疾类别
     *
     * @param disabilityType 残疾类别
     */
    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    /**
     * 获取残疾等级
     *
     * @return disability_rank - 残疾等级
     */
    public String getDisabilityRank() {
        return disabilityRank;
    }

    /**
     * 设置残疾等级
     *
     * @param disabilityRank 残疾等级
     */
    public void setDisabilityRank(String disabilityRank) {
        this.disabilityRank = disabilityRank;
    }

    /**
     * 获取残疾证号
     *
     * @return disability_no - 残疾证号
     */
    public String getDisabilityNo() {
        return disabilityNo;
    }

    /**
     * 设置残疾证号
     *
     * @param disabilityNo 残疾证号
     */
    public void setDisabilityNo(String disabilityNo) {
        this.disabilityNo = disabilityNo;
    }

    /**
     * 获取残疾证发放日期
     *
     * @return disability_card_date - 残疾证发放日期
     */
    public Date getDisabilityCardDate() {
        return disabilityCardDate;
    }

    /**
     * 设置残疾证发放日期
     *
     * @param disabilityCardDate 残疾证发放日期
     */
    public void setDisabilityCardDate(Date disabilityCardDate) {
        this.disabilityCardDate = disabilityCardDate;
    }

    /**
     * 获取证件有效期开始时间
     *
     * @return validity_certificate_start - 证件有效期开始时间
     */
    public Date getValidityCertificateStart() {
        return validityCertificateStart;
    }

    /**
     * 设置证件有效期开始时间
     *
     * @param validityCertificate_start 证件有效期开始时间
     */
    public void setValidityCertificateStart(Date validityCertificateStart) {
        this.validityCertificateStart = validityCertificateStart;
    }

    /**
     * 获取证件有效期结束时间
     *
     * @return validity_certificate_end - 证件有效期结束时间
     */
    public Date getValidityCertificateEnd() {
        return validityCertificateEnd;
    }

    /**
     * 设置证件有效期结束时间
     *
     * @param validityCertificate_end 证件有效期结束时间
     */
    public void setValidityCertificateEnd(Date validityCertificateEnd) {
        this.validityCertificateEnd = validityCertificateEnd;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}