package com.champlink.common.form.staff.baseInfo;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.BaseForm;

public class SearchBaseInfoForm extends BaseForm {

    /**
     * ids
     */
    private String ids;

    /**
     * 离职日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;

    /**
     * 是否加入黑名单
     */
    private Long isBlacklist;

    /**
     * 员工id
     */
    private Long staffId;

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 所有子部门ID
     */
    private List<Org> deptIds;

    /**
     * 证件号码
     */
    private String identityNo;

    /**
     * 在职状态
     */
    private Long jobStatus;
    
    private List<Long> staffIdList;

    /**
     * 基地ID
     */
    private Long baseId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Long jobStatus) {
        this.jobStatus = jobStatus;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<Org> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Org> deptIds) {
        this.deptIds = deptIds;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Long getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Long isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}
    
}