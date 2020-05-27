package com.champlink.common.domain.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class OccupationalInjury extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 工伤类别
     */
    private Long occupationalInjuryTyle;

    /**
     * 工伤发生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date occupationalInjuryStartDate;

    /**
     * 伤残程度鉴定时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date disabilityIdentificationTime;

    /**
     * 伤残等级
     */
    private Long disabilityLevel;

    /**
     * 认定部位或名称
     */
    private String partName;

    /**
     * 工伤认定时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date occupationalInjuryTime;

    /**
     * 护理程度级别
     */
    private Long nurseLevel;

    /**
     * 工伤号
     */
    private String occupationalInjuryNo;

    /**
     * 伤前12个月平均月缴费工资
     */
    private Double beforeInjurySalary;

    /**
     * 事故类别
     */
    private Long accidentState;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取工伤类别
     *
     * @return occupational_injury_tyle - 工伤类别
     */
    public Long getOccupationalInjuryTyle() {
        return occupationalInjuryTyle;
    }

    /**
     * 设置工伤类别
     *
     * @param occupationalInjuryTyle 工伤类别
     */
    public void setOccupationalInjuryTyle(Long occupationalInjuryTyle) {
        this.occupationalInjuryTyle = occupationalInjuryTyle;
    }

    /**
     * 获取工伤发生日期
     *
     * @return occupational_injury_start_date - 工伤发生日期
     */
    public Date getOccupationalInjuryStartDate() {
        return occupationalInjuryStartDate;
    }

    /**
     * 设置工伤发生日期
     *
     * @param occupationalInjuryStartDate 工伤发生日期
     */
    public void setOccupationalInjuryStartDate(Date occupationalInjuryStartDate) {
        this.occupationalInjuryStartDate = occupationalInjuryStartDate;
    }

    /**
     * 获取伤残程度鉴定时间
     *
     * @return disability_identification_time - 伤残程度鉴定时间
     */
    public Date getDisabilityIdentificationTime() {
        return disabilityIdentificationTime;
    }

    /**
     * 设置伤残程度鉴定时间
     *
     * @param disabilityIdentificationTime 伤残程度鉴定时间
     */
    public void setDisabilityIdentificationTime(Date disabilityIdentificationTime) {
        this.disabilityIdentificationTime = disabilityIdentificationTime;
    }

    /**
     * 获取伤残等级
     *
     * @return disability_level - 伤残等级
     */
    public Long getDisabilityLevel() {
        return disabilityLevel;
    }

    /**
     * 设置伤残等级
     *
     * @param disabilityLevel 伤残等级
     */
    public void setDisabilityLevel(Long disabilityLevel) {
        this.disabilityLevel = disabilityLevel;
    }

    /**
     * 获取认定部位或名称
     *
     * @return part_name - 认定部位或名称
     */
    public String getPartName() {
        return partName;
    }

    /**
     * 设置认定部位或名称
     *
     * @param partName 认定部位或名称
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * 获取工伤认定时间
     *
     * @return occupational injury_time - 工伤认定时间
     */
    public Date getOccupationalInjuryTime() {
        return occupationalInjuryTime;
    }

    /**
     * 设置工伤认定时间
     *
     * @param occupationalInjuryTime 工伤认定时间
     */
    public void setOccupationalInjuryTime(Date occupationalInjuryTime) {
        this.occupationalInjuryTime = occupationalInjuryTime;
    }

    /**
     * 获取护理程度级别
     *
     * @return nurse_level - 护理程度级别
     */
    public Long getNurseLevel() {
        return nurseLevel;
    }

    /**
     * 设置护理程度级别
     *
     * @param nurseLevel 护理程度级别
     */
    public void setNurseLevel(Long nurseLevel) {
        this.nurseLevel = nurseLevel;
    }

    /**
     * 获取工伤号
     *
     * @return occupational injury_no - 工伤号
     */
    public String getOccupationalInjuryNo() {
        return occupationalInjuryNo;
    }

    /**
     * 设置工伤号
     *
     * @param occupationalInjuryNo 工伤号
     */
    public void setOccupationalInjuryNo(String occupationalInjuryNo) {
        this.occupationalInjuryNo = occupationalInjuryNo;
    }

    /**
     * 获取伤前12个月平均月缴费工资
     *
     * @return before_injury_salary - 伤前12个月平均月缴费工资
     */
    public Double getBeforeInjurySalary() {
        return beforeInjurySalary;
    }

    /**
     * 设置伤前12个月平均月缴费工资
     *
     * @param beforeInjurySalary 伤前12个月平均月缴费工资
     */
    public void setBeforeInjurySalary(Double beforeInjurySalary) {
        this.beforeInjurySalary = beforeInjurySalary;
    }

    /**
     * 获取事故类别
     *
     * @return accident_state - 事故类别
     */
    public Long getAccidentState() {
        return accidentState;
    }

    /**
     * 设置事故类别
     *
     * @param accidentState 事故类别
     */
    public void setAccidentState(Long accidentState) {
        this.accidentState = accidentState;
    }

}