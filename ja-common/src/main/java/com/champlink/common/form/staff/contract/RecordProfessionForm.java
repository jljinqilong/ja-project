package com.champlink.common.form.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.champlink.common.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RecordProfessionForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 职称级别
     */
    private String professionalTitleRank;

    /**
     * 取得资格时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date qualifyTime;

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
     * 获取证书名称
     *
     * @return certificate_name - 证书名称
     */
    public String getCertificateName() {
        return certificateName;
    }

    /**
     * 设置证书名称
     *
     * @param certificateName 证书名称
     */
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    /**
     * 获取职称级别
     *
     * @return professional_title_rank - 职称级别
     */
    public String getProfessionalTitleRank() {
        return professionalTitleRank;
    }

    /**
     * 设置职称级别
     *
     * @param professionalTitleRank 职称级别
     */
    public void setProfessionalTitleRank(String professionalTitleRank) {
        this.professionalTitleRank = professionalTitleRank;
    }

    /**
     * 获取取得资格时间
     *
     * @return qualify_time - 取得资格时间
     */
    public Date getQualifyTime() {
        return qualifyTime;
    }

    /**
     * 设置取得资格时间
     *
     * @param qualifyTime 取得资格时间
     */
    public void setQualifyTime(Date qualifyTime) {
        this.qualifyTime = qualifyTime;
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