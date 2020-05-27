package com.champlink.common.domain.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class RecordProfession extends BaseBean {

    private static final long serialVersionUID = 1L;

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
    @JSONField(format = "yyyy-MM-dd")
    private Date qualifyTime;

    /**
     * 备注
     */
    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date certificateStartDate;//证书发放日期

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date certificateEndDate;//证书有效期限

    private String grantOrg;//发放机构

    private String grantLevel;//发放级别

    public Date getCertificateStartDate() {
        return certificateStartDate;
    }

    public void setCertificateStartDate(Date certificateStartDate) {
        this.certificateStartDate = certificateStartDate;
    }

    public Date getCertificateEndDate() {
        return certificateEndDate;
    }

    public void setCertificateEndDate(Date certificateEndDate) {
        this.certificateEndDate = certificateEndDate;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getGrantLevel() {
        return grantLevel;
    }

    public void setGrantLevel(String grantLevel) {
        this.grantLevel = grantLevel;
    }

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