package com.champlink.common.form.staff.baseInfo;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.BaseForm;

public class SearchAdjustmentWork extends BaseForm {

    /**
     * ids
     */
    private String ids;

    /**
     * 员工id
     */
    private Long staffId;

    /**
     * 异动类型
     */
    private String changeType;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 证件号码
     */
    private String identityNo;

    /**
     * 所有子部门
     */
    private List<Org> deptIds;

    /**
     * 创建开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 创建结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    private List<Long> staffIdList;
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Org> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Org> deptIds) {
        this.deptIds = deptIds;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}

}
