package com.champlink.common.domain.attendance;

import java.util.Date;
import com.champlink.common.domain.BaseBean;

/**
 * 加班单
 * 
 * @author natyu
 * @date 2018年8月21日 下午7:09:58
 *
 */
public class OvertimeSheet extends BaseBean {

    /**
     * 基地ID
     */
    private Long baseId;

    /**
     * 基地名称
     */
    private String baseName;

    /**
     * 厂别ID
     */
    private Long factoryId;

    /**
     * 厂别名称
     */
    private String factoryName;

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

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 加班时长
     */
    private Double overtimeTime;

    /**
     * 加班原因
     */
    private String reason;

    /**
     * 获取基地ID
     *
     * @return base_id - 基地ID
     */
    public Long getBaseId() {
        return baseId;
    }

    /**
     * 设置基地ID
     *
     * @param baseId 基地ID
     */
    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    /**
     * 获取基地名称
     *
     * @return base_name - 基地名称
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * 设置基地名称
     *
     * @param baseName 基地名称
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    /**
     * 获取厂别ID
     *
     * @return factory_id - 厂别ID
     */
    public Long getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂别ID
     *
     * @param factoryId 厂别ID
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取厂别名称
     *
     * @return factory_name - 厂别名称
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置厂别名称
     *
     * @param factoryName 厂别名称
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
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

    public Double getOvertimeTime() {
		return overtimeTime;
	}

	public void setOvertimeTime(Double overtimeTime) {
		this.overtimeTime = overtimeTime;
	}

	/**
     * 获取开始时间
     *
     * @return start_date - 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间
     *
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * 获取加班原因
     *
     * @return reason - 加班原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置加班原因
     *
     * @param reason 加班原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}