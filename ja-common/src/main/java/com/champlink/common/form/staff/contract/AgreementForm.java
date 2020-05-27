package com.champlink.common.form.staff.contract;

import java.util.List;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.BaseForm;

public class AgreementForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    private String agreementNo;

    private Integer agreementType;

    private Integer agreementState;

    private Integer renewStatus;

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 基地
     */
    private Long baseId;

    private Long deptId;

    /**
     * 所有子部门
     */
    private List<Org> deptIds;
    
    private List<Long> staffIdList;

    public List<Org> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Org> deptIds) {
        this.deptIds = deptIds;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Integer getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Integer agreementType) {
        this.agreementType = agreementType;
    }

    public Integer getAgreementState() {
        return agreementState;
    }

    public void setAgreementState(Integer agreementState) {
        this.agreementState = agreementState;
    }

    public Integer getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(Integer renewStatus) {
        this.renewStatus = renewStatus;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}

}