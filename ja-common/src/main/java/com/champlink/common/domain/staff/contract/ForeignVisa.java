package com.champlink.common.domain.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class ForeignVisa extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 工作许可编号
     */
    private String workPermitNo;

    /**
     * 发证日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date certifyingDate;

    /**
     * 发证机关
     */
    private String certifyingAuthority;

    /**
     * 有效期开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 有效期结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 年检时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date annualInspectionDate;

    /**
     * 附件
     */
    private String file;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取工作许可编号
     *
     * @return work permit_no - 工作许可编号
     */
    public String getWorkPermitNo() {
        return workPermitNo;
    }

    /**
     * 设置工作许可编号
     *
     * @param workPermitNo 工作许可编号
     */
    public void setWorkPermitNo(String workPermitNo) {
        this.workPermitNo = workPermitNo;
    }

    /**
     * 获取发证日期
     *
     * @return certifying_date - 发证日期
     */
    public Date getCertifyingDate() {
        return certifyingDate;
    }

    /**
     * 设置发证日期
     *
     * @param certifyingDate 发证日期
     */
    public void setCertifyingDate(Date certifyingDate) {
        this.certifyingDate = certifyingDate;
    }

    /**
     * 获取发证机关
     *
     * @return certifying_authority - 发证机关
     */
    public String getCertifyingAuthority() {
        return certifyingAuthority;
    }

    /**
     * 设置发证机关
     *
     * @param certifyingAuthority 发证机关
     */
    public void setCertifyingAuthority(String certifyingAuthority) {
        this.certifyingAuthority = certifyingAuthority;
    }

    /**
     * 获取有效期开始时间
     *
     * @return start_date - 有效期开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置有效期开始时间
     *
     * @param startDate 有效期开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取有效期结束时间
     *
     * @return end_date - 有效期结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置有效期结束时间
     *
     * @param endDate 有效期结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取年检时间
     *
     * @return annual_inspection_date - 年检时间
     */
    public Date getAnnualInspectionDate() {
        return annualInspectionDate;
    }

    /**
     * 设置年检时间
     *
     * @param annualInspectionDate 年检时间
     */
    public void setAnnualInspectionDate(Date annualInspectionDate) {
        this.annualInspectionDate = annualInspectionDate;
    }

    /**
     * 获取附件
     *
     * @return file - 附件
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置附件
     *
     * @param file 附件
     */
    public void setFile(String file) {
        this.file = file;
    }

}