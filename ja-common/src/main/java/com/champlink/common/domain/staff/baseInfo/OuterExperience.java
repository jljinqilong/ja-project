package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

/**
 * 外部工作经历
 * 
 * @author natyu
 * @date 2018年7月21日 上午10:59:00
 *
 */
public class OuterExperience extends BaseBean {

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 任职部门
     */
    private String deptName;

    /**
     * 任职职衔
     */
    private String positionName;

    /**
     * 开始日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 证明人及职务
     */
    private String proofPersonAndDuty;

    /**
     * 证明人联系方式
     */
    private String proofContact;

    /**
     * 薪酬状况
     */
    private String salary;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 获取人员信息ID
     *
     * @return staff_id - 人员信息ID
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置人员信息ID
     *
     * @param staffId 人员信息ID
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取工作单位
     *
     * @return work_unit - 工作单位
     */
    public String getWorkUnit() {
        return workUnit;
    }

    /**
     * 设置工作单位
     *
     * @param workUnit 工作单位
     */
    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    /**
     * 获取任职部门
     *
     * @return dept_name - 任职部门
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置任职部门
     *
     * @param deptName 任职部门
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取任职职衔
     *
     * @return position_name - 任职职衔
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 设置任职职衔
     *
     * @param positionName 任职职衔
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
     * 获取证明人及职务
     *
     * @return proof_person_and_duty - 证明人及职务
     */
    public String getProofPersonAndDuty() {
        return proofPersonAndDuty;
    }

    /**
     * 设置证明人及职务
     *
     * @param proofPersonAndDuty 证明人及职务
     */
    public void setProofPersonAndDuty(String proofPersonAndDuty) {
        this.proofPersonAndDuty = proofPersonAndDuty;
    }

    /**
     * 获取证明人联系方式
     *
     * @return proof_contact - 证明人联系方式
     */
    public String getProofContact() {
        return proofContact;
    }

    /**
     * 设置证明人联系方式
     *
     * @param proofContact 证明人联系方式
     */
    public void setProofContact(String proofContact) {
        this.proofContact = proofContact;
    }

    /**
     * 获取薪酬状况
     *
     * @return salary - 薪酬状况
     */
    public String getSalary() {
        return salary;
    }

    /**
     * 设置薪酬状况
     *
     * @param salary 薪酬状况
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

}