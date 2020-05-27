package com.champlink.common.domain.attendance;

import java.util.Date;
import com.champlink.common.domain.BaseBean;

public class PersonalLeave extends BaseBean {

    /**
     * 员工ID
     */
    private Long staffId;

    /**
     * 员工工号
     */
    private String staffNo;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 班次ID
     */
    private Long jobNoId;

    /**
     * 班次名称
     */
    private String jobNoName;

    private Long baseId;
    
    private String baseName;
    
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请假类型ID
     */
    private Long holidayTypeId;

    /**
     * 请假名称
     */
    private String holidayTypeName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 代理人ID
     */
    private Long agentStaffId;

    /**
     * 代理人工号
     */
    private String agentStaffNo;

    /**
     * 代理人姓名
     */
    private String agentStaffName;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 时长
     */
    private Double hours;
    
    /**
     * 实际开始时间
     */
    private Date realStartDate;
    
    /**
     * 实际结束时间
     */
    private Date realEndDate;
    
    /**
     * 实际时长
     */
    private Double realHours;

    public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	/**
     * 获取员工ID
     *
     * @return staff_id - 员工ID
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置员工ID
     *
     * @param staffId 员工ID
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取员工工号
     *
     * @return staff_no - 员工工号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置员工工号
     *
     * @param staffNo 员工工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取员工姓名
     *
     * @return staff_name - 员工姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置员工姓名
     *
     * @param staffName 员工姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取班次ID
     *
     * @return job_no_id - 班次ID
     */
    public Long getJobNoId() {
        return jobNoId;
    }

    /**
     * 设置班次ID
     *
     * @param jobNoId 班次ID
     */
    public void setJobNoId(Long jobNoId) {
        this.jobNoId = jobNoId;
    }

    /**
     * 获取班次名称
     *
     * @return job_no_name - 班次名称
     */
    public String getJobNoName() {
        return jobNoName;
    }

    /**
     * 设置班次名称
     *
     * @param jobNoName 班次名称
     */
    public void setJobNoName(String jobNoName) {
        this.jobNoName = jobNoName;
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门名称
     *
     * @return dept_name - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取请假类型ID
     *
     * @return holiday_type_id - 请假类型ID
     */
    public Long getHolidayTypeId() {
        return holidayTypeId;
    }

    /**
     * 设置请假类型ID
     *
     * @param holidayTypeId 请假类型ID
     */
    public void setHolidayTypeId(Long holidayTypeId) {
        this.holidayTypeId = holidayTypeId;
    }

    /**
     * 获取请假名称
     *
     * @return holiday_type_name - 请假名称
     */
    public String getHolidayTypeName() {
        return holidayTypeName;
    }

    /**
     * 设置请假名称
     *
     * @param holidayTypeName 请假名称
     */
    public void setHolidayTypeName(String holidayTypeName) {
        this.holidayTypeName = holidayTypeName;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取代理人ID
     *
     * @return agent_staff_id - 代理人ID
     */
    public Long getAgentStaffId() {
        return agentStaffId;
    }

    /**
     * 设置代理人ID
     *
     * @param agentStaffId 代理人ID
     */
    public void setAgentStaffId(Long agentStaffId) {
        this.agentStaffId = agentStaffId;
    }

    /**
     * 获取代理人工号
     *
     * @return agent_staff_no - 代理人工号
     */
    public String getAgentStaffNo() {
        return agentStaffNo;
    }

    /**
     * 设置代理人工号
     *
     * @param agentStaffNo 代理人工号
     */
    public void setAgentStaffNo(String agentStaffNo) {
        this.agentStaffNo = agentStaffNo;
    }

    /**
     * 获取代理人姓名
     *
     * @return agent_staff_name - 代理人姓名
     */
    public String getAgentStaffName() {
        return agentStaffName;
    }

    /**
     * 设置代理人姓名
     *
     * @param agentStaffName 代理人姓名
     */
    public void setAgentStaffName(String agentStaffName) {
        this.agentStaffName = agentStaffName;
    }

    /**
     * 获取开始日期
     *
     * @return start_date - 开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始日期
     *
     * @param startDate 开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束日期
     *
     * @return end_date - 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取时长
     *
     * @return hours - 时长
     */
	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public Date getRealStartDate() {
		return realStartDate;
	}

	public void setRealStartDate(Date realStartDate) {
		this.realStartDate = realStartDate;
	}

	public Date getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}

	public Double getRealHours() {
		return realHours;
	}

	public void setRealHours(Double realHours) {
		this.realHours = realHours;
	}
	
}