package com.champlink.common.domain.attendance;

import java.util.Date;

import com.champlink.common.domain.BaseBean;

/**
 * 刷卡日志
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:37:51
 *
 */
public class CreditCardLog extends BaseBean {

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
     * 组织架构ID
     */
    private Long orgId;

    /**
     * 组织架构名称
     */
    private String orgName;

    /**
     * 状态：0：有效，1：无效
     */
    private Integer status;

    /**
     * 考勤机id
     */
    private Long machineId;
    
    /**
     * 考勤机名称
     */
    private String machineName;
    
    /**
     * 刷卡时间
     */
    private Date time;

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
     * 获取组织架构ID
     *
     * @return org_id - 组织架构ID
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置组织架构ID
     *
     * @param orgId 组织架构ID
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取组织架构名称
     *
     * @return org_name - 组织架构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置组织架构名称
     *
     * @param orgName 组织架构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取状态：0：有效，1：无效
     *
     * @return status - 状态：0：有效，1：无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0：有效，1：无效
     *
     * @param status 状态：0：有效，1：无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    

    public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	/**
     * 获取刷卡时间
     *
     * @return time - 刷卡时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置刷卡时间
     *
     * @param time 刷卡时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

}