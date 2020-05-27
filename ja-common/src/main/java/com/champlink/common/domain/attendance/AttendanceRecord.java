package com.champlink.common.domain.attendance;

import java.util.Date;

public class AttendanceRecord {
    private Long rowId;

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 基地ID（org_id）
     */
    private Long baseId;

    /**
     * 基地名称
     */
    private String baseName;

    /**
     * 部门ID（org_id）
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 班次ID（atd_job_number）
     */
    private Long jobNumId;

    /**
     * 班次名称
     */
    private String jobNumName;

    /**
     * 岗位（sys_code）
     */
    private Long operatingPostId;

    /**
     * 岗位名称
     */
    private String operatingPostName;

    /**
     * 职位ID（org_position）
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 职级ID（org_rank）
     */
    private Long rankId;

    /**
     * 职级名称
     */
    private String rankName;

    /**
     * 职等ID（org_grade）
     */
    private Long gradeId;

    /**
     * 职等名称
     */
    private String gradeName;

    /**
     * 内部职级ID（org_rank）
     */
    private Long internalRankId;

    /**
     * 内部职级名称
     */
    private String internalRankName;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 总出勤
     */
    private Double totalAttendance;

    /**
     * 应出勤天数
     */
    private Double attendanceDays;

    /**
     * 前夜
     */
    private Double beforeNight;

    /**
     * 后夜
     */
    private Double lateNight;

    /**
     * 加班天数
     */
    private Double overtimeDays;

    /**
     * 值班天数
     */
    private Double dutyDays;

    /**
     * 节假日加班
     */
    private Double holidayOvertime;

    /**
     * 节假日值班
     */
    private Double dutyHolidays;

    /**
     * 事假
     */
    private Double compassionateLeave;

    /**
     * 带薪病假
     */
    private Double paidSickLeave;

    /**
     * 病假
     */
    private Double sickLeave;

    /**
     * 旷工
     */
    private Double absenteeism;

    /**
     * 丧假
     */
    private Double bereavement;

    /**
     * 婚假
     */
    private Double marriageHoliday;

    /**
     * 产假
     */
    private Double maternityLeave;

    /**
     * 工伤
     */
    private Double injuryJob;

    /**
     * 年假
     */
    private Double annualLeave;

    /**
     * 夜班餐补天数
     */
    private Double nightShiftDays;

    /**
     * 节假日加班工资
     */
    private Double overtimePayDuringHolidays;

    /**
     * 节假日值班工资
     */
    private Double dutyPayHolidays;

    /**
     * 前夜班补助
     */
    private Double beforeNightShiftSubsidy;

    /**
     * 后夜班补助
     */
    private Double lateNightShiftSubsidy;

    /**
     * 日常加班
     */
    private Double dailyOvertime;

    /**
     * 转正前出勤
     */
    private Double attendanceBeforeCorrection;

    /**
     * 转正后出勤
     */
    private Double attendanceAfterCorrection;

    /**
     * 转正前加班
     */
    private Double overtimeBeforeCorrection;

    /**
     * 转正后加班
     */
    private Double overtimeAfterCorrection;

    /**
     * 转正前应出勤
     */
    private Double attendanceShouldBeforeCorrection;

    /**
     * 转正后应出勤
     */
    private Double attendanceShouldAfterCorrection;

    /**
     * 转正前节假日加班
     */
    private Double workingOvertimeBeforeHolidays;

    /**
     * 转正后节假日加班
     */
    private Double workingOvertimeAfterHolidays;

    /**
     * 迟到
     */
    private Double late;

    /**
     * 早退
     */
    private Double earlyRetreat;

    /**
     * 产检
     */
    private Double productionInspection;

    /**
     * 哺乳假
     */
    private Double lactationLeave;

    /**
     * 探亲
     */
    private Double visitFamily;

    /**
     * 是否有效（0：有效；1：无效）
     */
    private Integer delFlag;

    /**
     * @return row_id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * @param rowId
     */
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    /**
     * 获取工号
     *
     * @return staff_no - 工号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置工号
     *
     * @param staffNo 工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取姓名
     *
     * @return staff_name - 姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置姓名
     *
     * @param staffName 姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取基地ID（org_id）
     *
     * @return base_id - 基地ID（org_id）
     */
    public Long getBaseId() {
        return baseId;
    }

    /**
     * 设置基地ID（org_id）
     *
     * @param baseId 基地ID（org_id）
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
     * 获取部门ID（org_id）
     *
     * @return dept_id - 部门ID（org_id）
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID（org_id）
     *
     * @param deptId 部门ID（org_id）
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
     * 获取班次ID（atd_job_number）
     *
     * @return job_num_id - 班次ID（atd_job_number）
     */
    public Long getJobNumId() {
        return jobNumId;
    }

    /**
     * 设置班次ID（atd_job_number）
     *
     * @param jobNumId 班次ID（atd_job_number）
     */
    public void setJobNumId(Long jobNumId) {
        this.jobNumId = jobNumId;
    }

    /**
     * 获取班次名称
     *
     * @return job_num_name - 班次名称
     */
    public String getJobNumName() {
        return jobNumName;
    }

    /**
     * 设置班次名称
     *
     * @param jobNumName 班次名称
     */
    public void setJobNumName(String jobNumName) {
        this.jobNumName = jobNumName;
    }

    /**
     * 获取岗位（sys_code）
     *
     * @return operating_post_id - 岗位（sys_code）
     */
    public Long getOperatingPostId() {
        return operatingPostId;
    }

    /**
     * 设置岗位（sys_code）
     *
     * @param operatingPostId 岗位（sys_code）
     */
    public void setOperatingPostId(Long operatingPostId) {
        this.operatingPostId = operatingPostId;
    }

    /**
     * 获取岗位名称
     *
     * @return operating_post_name - 岗位名称
     */
    public String getOperatingPostName() {
        return operatingPostName;
    }

    /**
     * 设置岗位名称
     *
     * @param operatingPostName 岗位名称
     */
    public void setOperatingPostName(String operatingPostName) {
        this.operatingPostName = operatingPostName;
    }

    /**
     * 获取职位ID（org_position）
     *
     * @return position_id - 职位ID（org_position）
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * 设置职位ID（org_position）
     *
     * @param positionId 职位ID（org_position）
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取职位名称
     *
     * @return position_name - 职位名称
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 设置职位名称
     *
     * @param positionName 职位名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * 获取职级ID（org_rank）
     *
     * @return rank_id - 职级ID（org_rank）
     */
    public Long getRankId() {
        return rankId;
    }

    /**
     * 设置职级ID（org_rank）
     *
     * @param rankId 职级ID（org_rank）
     */
    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    /**
     * 获取职级名称
     *
     * @return rank_name - 职级名称
     */
    public String getRankName() {
        return rankName;
    }

    /**
     * 设置职级名称
     *
     * @param rankName 职级名称
     */
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    /**
     * 获取职等ID（org_grade）
     *
     * @return grade_id - 职等ID（org_grade）
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * 设置职等ID（org_grade）
     *
     * @param gradeId 职等ID（org_grade）
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取职等名称
     *
     * @return grade_name - 职等名称
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 设置职等名称
     *
     * @param gradeName 职等名称
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取内部职级ID（org_rank）
     *
     * @return internal_rank_id - 内部职级ID（org_rank）
     */
    public Long getInternalRankId() {
        return internalRankId;
    }

    /**
     * 设置内部职级ID（org_rank）
     *
     * @param internalRankId 内部职级ID（org_rank）
     */
    public void setInternalRankId(Long internalRankId) {
        this.internalRankId = internalRankId;
    }

    /**
     * 获取内部职级名称
     *
     * @return internal_rank__name - 内部职级名称
     */
    public String getInternalRankName() {
        return internalRankName;
    }

    /**
     * 设置内部职级名称
     *
     * @param internalRankName 内部职级名称
     */
    public void setInternalRankName(String internalRankName) {
        this.internalRankName = internalRankName;
    }

    /**
     * 获取入职日期
     *
     * @return entry_date - 入职日期
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * 设置入职日期
     *
     * @param entryDate 入职日期
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * 获取总出勤
     *
     * @return total_attendance - 总出勤
     */
    public Double getTotalAttendance() {
        return totalAttendance;
    }

    /**
     * 设置总出勤
     *
     * @param totalAttendance 总出勤
     */
    public void setTotalAttendance(Double totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    /**
     * 获取应出勤天数
     *
     * @return attendance_days - 应出勤天数
     */
    public Double getAttendanceDays() {
        return attendanceDays;
    }

    /**
     * 设置应出勤天数
     *
     * @param attendanceDays 应出勤天数
     */
    public void setAttendanceDays(Double attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    /**
     * 获取前夜
     *
     * @return before_night - 前夜
     */
    public Double getBeforeNight() {
        return beforeNight;
    }

    /**
     * 设置前夜
     *
     * @param beforeNight 前夜
     */
    public void setBeforeNight(Double beforeNight) {
        this.beforeNight = beforeNight;
    }

    /**
     * 获取后夜
     *
     * @return late_night - 后夜
     */
    public Double getLateNight() {
        return lateNight;
    }

    /**
     * 设置后夜
     *
     * @param lateNight 后夜
     */
    public void setLateNight(Double lateNight) {
        this.lateNight = lateNight;
    }

    /**
     * 获取加班天数
     *
     * @return overtime_days - 加班天数
     */
    public Double getOvertimeDays() {
        return overtimeDays;
    }

    /**
     * 设置加班天数
     *
     * @param overtimeDays 加班天数
     */
    public void setOvertimeDays(Double overtimeDays) {
        this.overtimeDays = overtimeDays;
    }

    /**
     * 获取值班天数
     *
     * @return duty_days - 值班天数
     */
    public Double getDutyDays() {
        return dutyDays;
    }

    /**
     * 设置值班天数
     *
     * @param dutyDays 值班天数
     */
    public void setDutyDays(Double dutyDays) {
        this.dutyDays = dutyDays;
    }

    /**
     * 获取节假日加班
     *
     * @return holiday_overtime - 节假日加班
     */
    public Double getHolidayOvertime() {
        return holidayOvertime;
    }

    /**
     * 设置节假日加班
     *
     * @param holidayOvertime 节假日加班
     */
    public void setHolidayOvertime(Double holidayOvertime) {
        this.holidayOvertime = holidayOvertime;
    }

    /**
     * 获取节假日值班
     *
     * @return duty_holidays - 节假日值班
     */
    public Double getDutyHolidays() {
        return dutyHolidays;
    }

    /**
     * 设置节假日值班
     *
     * @param dutyHolidays 节假日值班
     */
    public void setDutyHolidays(Double dutyHolidays) {
        this.dutyHolidays = dutyHolidays;
    }

    /**
     * 获取事假
     *
     * @return compassionate_leave - 事假
     */
    public Double getCompassionateLeave() {
        return compassionateLeave;
    }

    /**
     * 设置事假
     *
     * @param compassionateLeave 事假
     */
    public void setCompassionateLeave(Double compassionateLeave) {
        this.compassionateLeave = compassionateLeave;
    }

    /**
     * 获取带薪病假
     *
     * @return paid_sick_leave - 带薪病假
     */
    public Double getPaidSickLeave() {
        return paidSickLeave;
    }

    /**
     * 设置带薪病假
     *
     * @param paidSickLeave 带薪病假
     */
    public void setPaidSickLeave(Double paidSickLeave) {
        this.paidSickLeave = paidSickLeave;
    }

    /**
     * 获取病假
     *
     * @return sick_leave - 病假
     */
    public Double getSickLeave() {
        return sickLeave;
    }

    /**
     * 设置病假
     *
     * @param sickLeave 病假
     */
    public void setSickLeave(Double sickLeave) {
        this.sickLeave = sickLeave;
    }

    /**
     * 获取旷工
     *
     * @return absenteeism - 旷工
     */
    public Double getAbsenteeism() {
        return absenteeism;
    }

    /**
     * 设置旷工
     *
     * @param absenteeism 旷工
     */
    public void setAbsenteeism(Double absenteeism) {
        this.absenteeism = absenteeism;
    }

    /**
     * 获取丧假
     *
     * @return bereavement - 丧假
     */
    public Double getBereavement() {
        return bereavement;
    }

    /**
     * 设置丧假
     *
     * @param bereavement 丧假
     */
    public void setBereavement(Double bereavement) {
        this.bereavement = bereavement;
    }

    /**
     * 获取婚假
     *
     * @return marriage_holiday - 婚假
     */
    public Double getMarriageHoliday() {
        return marriageHoliday;
    }

    /**
     * 设置婚假
     *
     * @param marriageHoliday 婚假
     */
    public void setMarriageHoliday(Double marriageHoliday) {
        this.marriageHoliday = marriageHoliday;
    }

    /**
     * 获取产假
     *
     * @return maternity_leave - 产假
     */
    public Double getMaternityLeave() {
        return maternityLeave;
    }

    /**
     * 设置产假
     *
     * @param maternityLeave 产假
     */
    public void setMaternityLeave(Double maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    /**
     * 获取工伤
     *
     * @return injury_job - 工伤
     */
    public Double getInjuryJob() {
        return injuryJob;
    }

    /**
     * 设置工伤
     *
     * @param injuryJob 工伤
     */
    public void setInjuryJob(Double injuryJob) {
        this.injuryJob = injuryJob;
    }

    /**
     * 获取年假
     *
     * @return annual_leave - 年假
     */
    public Double getAnnualLeave() {
        return annualLeave;
    }

    /**
     * 设置年假
     *
     * @param annualLeave 年假
     */
    public void setAnnualLeave(Double annualLeave) {
        this.annualLeave = annualLeave;
    }

    /**
     * 获取夜班餐补天数
     *
     * @return night_shift_days - 夜班餐补天数
     */
    public Double getNightShiftDays() {
        return nightShiftDays;
    }

    /**
     * 设置夜班餐补天数
     *
     * @param nightShiftDays 夜班餐补天数
     */
    public void setNightShiftDays(Double nightShiftDays) {
        this.nightShiftDays = nightShiftDays;
    }

    /**
     * 获取节假日加班工资
     *
     * @return overtime_pay_during_holidays - 节假日加班工资
     */
    public Double getOvertimePayDuringHolidays() {
        return overtimePayDuringHolidays;
    }

    /**
     * 设置节假日加班工资
     *
     * @param overtimePayDuringHolidays 节假日加班工资
     */
    public void setOvertimePayDuringHolidays(Double overtimePayDuringHolidays) {
        this.overtimePayDuringHolidays = overtimePayDuringHolidays;
    }

    /**
     * 获取节假日值班工资
     *
     * @return duty_pay_holidays - 节假日值班工资
     */
    public Double getDutyPayHolidays() {
        return dutyPayHolidays;
    }

    /**
     * 设置节假日值班工资
     *
     * @param dutyPayHolidays 节假日值班工资
     */
    public void setDutyPayHolidays(Double dutyPayHolidays) {
        this.dutyPayHolidays = dutyPayHolidays;
    }

    /**
     * 获取前夜班补助
     *
     * @return before_night_shift_subsidy - 前夜班补助
     */
    public Double getBeforeNightShiftSubsidy() {
        return beforeNightShiftSubsidy;
    }

    /**
     * 设置前夜班补助
     *
     * @param beforeNightShiftSubsidy 前夜班补助
     */
    public void setBeforeNightShiftSubsidy(Double beforeNightShiftSubsidy) {
        this.beforeNightShiftSubsidy = beforeNightShiftSubsidy;
    }

    /**
     * 获取后夜班补助
     *
     * @return late_night_shift_subsidy - 后夜班补助
     */
    public Double getLateNightShiftSubsidy() {
        return lateNightShiftSubsidy;
    }

    /**
     * 设置后夜班补助
     *
     * @param lateNightShiftSubsidy 后夜班补助
     */
    public void setLateNightShiftSubsidy(Double lateNightShiftSubsidy) {
        this.lateNightShiftSubsidy = lateNightShiftSubsidy;
    }

    /**
     * 获取日常加班
     *
     * @return daily_overtime - 日常加班
     */
    public Double getDailyOvertime() {
        return dailyOvertime;
    }

    /**
     * 设置日常加班
     *
     * @param dailyOvertime 日常加班
     */
    public void setDailyOvertime(Double dailyOvertime) {
        this.dailyOvertime = dailyOvertime;
    }

    /**
     * 获取转正前出勤
     *
     * @return attendance_before_correction - 转正前出勤
     */
    public Double getAttendanceBeforeCorrection() {
        return attendanceBeforeCorrection;
    }

    /**
     * 设置转正前出勤
     *
     * @param attendanceBeforeCorrection 转正前出勤
     */
    public void setAttendanceBeforeCorrection(Double attendanceBeforeCorrection) {
        this.attendanceBeforeCorrection = attendanceBeforeCorrection;
    }

    /**
     * 获取转正后出勤
     *
     * @return attendance_after_correction - 转正后出勤
     */
    public Double getAttendanceAfterCorrection() {
        return attendanceAfterCorrection;
    }

    /**
     * 设置转正后出勤
     *
     * @param attendanceAfterCorrection 转正后出勤
     */
    public void setAttendanceAfterCorrection(Double attendanceAfterCorrection) {
        this.attendanceAfterCorrection = attendanceAfterCorrection;
    }

    /**
     * 获取转正前加班
     *
     * @return overtime_before_correction - 转正前加班
     */
    public Double getOvertimeBeforeCorrection() {
        return overtimeBeforeCorrection;
    }

    /**
     * 设置转正前加班
     *
     * @param overtimeBeforeCorrection 转正前加班
     */
    public void setOvertimeBeforeCorrection(Double overtimeBeforeCorrection) {
        this.overtimeBeforeCorrection = overtimeBeforeCorrection;
    }

    /**
     * 获取转正后加班
     *
     * @return overtime_after_correction - 转正后加班
     */
    public Double getOvertimeAfterCorrection() {
        return overtimeAfterCorrection;
    }

    /**
     * 设置转正后加班
     *
     * @param overtimeAfterCorrection 转正后加班
     */
    public void setOvertimeAfterCorrection(Double overtimeAfterCorrection) {
        this.overtimeAfterCorrection = overtimeAfterCorrection;
    }

    /**
     * 获取转正前应出勤
     *
     * @return attendance_should_before_correction - 转正前应出勤
     */
    public Double getAttendanceShouldBeforeCorrection() {
        return attendanceShouldBeforeCorrection;
    }

    /**
     * 设置转正前应出勤
     *
     * @param attendanceShouldBeforeCorrection 转正前应出勤
     */
    public void setAttendanceShouldBeforeCorrection(Double attendanceShouldBeforeCorrection) {
        this.attendanceShouldBeforeCorrection = attendanceShouldBeforeCorrection;
    }

    /**
     * 获取转正后应出勤
     *
     * @return attendance_should_after_correction - 转正后应出勤
     */
    public Double getAttendanceShouldAfterCorrection() {
        return attendanceShouldAfterCorrection;
    }

    /**
     * 设置转正后应出勤
     *
     * @param attendanceShouldAfterCorrection 转正后应出勤
     */
    public void setAttendanceShouldAfterCorrection(Double attendanceShouldAfterCorrection) {
        this.attendanceShouldAfterCorrection = attendanceShouldAfterCorrection;
    }

    /**
     * 获取转正前节假日加班
     *
     * @return working_overtime_before_holidays - 转正前节假日加班
     */
    public Double getWorkingOvertimeBeforeHolidays() {
        return workingOvertimeBeforeHolidays;
    }

    /**
     * 设置转正前节假日加班
     *
     * @param workingOvertimeBeforeHolidays 转正前节假日加班
     */
    public void setWorkingOvertimeBeforeHolidays(Double workingOvertimeBeforeHolidays) {
        this.workingOvertimeBeforeHolidays = workingOvertimeBeforeHolidays;
    }

    /**
     * 获取转正后节假日加班
     *
     * @return working_overtime_after_holidays - 转正后节假日加班
     */
    public Double getWorkingOvertimeAfterHolidays() {
        return workingOvertimeAfterHolidays;
    }

    /**
     * 设置转正后节假日加班
     *
     * @param workingOvertimeAfterHolidays 转正后节假日加班
     */
    public void setWorkingOvertimeAfterHolidays(Double workingOvertimeAfterHolidays) {
        this.workingOvertimeAfterHolidays = workingOvertimeAfterHolidays;
    }

    /**
     * 获取迟到
     *
     * @return late - 迟到
     */
    public Double getLate() {
        return late;
    }

    /**
     * 设置迟到
     *
     * @param late 迟到
     */
    public void setLate(Double late) {
        this.late = late;
    }

    /**
     * 获取早退
     *
     * @return early_retreat - 早退
     */
    public Double getEarlyRetreat() {
        return earlyRetreat;
    }

    /**
     * 设置早退
     *
     * @param earlyRetreat 早退
     */
    public void setEarlyRetreat(Double earlyRetreat) {
        this.earlyRetreat = earlyRetreat;
    }

    /**
     * 获取产检
     *
     * @return production_inspection - 产检
     */
    public Double getProductionInspection() {
        return productionInspection;
    }

    /**
     * 设置产检
     *
     * @param productionInspection 产检
     */
    public void setProductionInspection(Double productionInspection) {
        this.productionInspection = productionInspection;
    }

    /**
     * 获取哺乳假
     *
     * @return lactation_leave - 哺乳假
     */
    public Double getLactationLeave() {
        return lactationLeave;
    }

    /**
     * 设置哺乳假
     *
     * @param lactationLeave 哺乳假
     */
    public void setLactationLeave(Double lactationLeave) {
        this.lactationLeave = lactationLeave;
    }

    /**
     * 获取探亲
     *
     * @return visit_family - 探亲
     */
    public Double getVisitFamily() {
        return visitFamily;
    }

    /**
     * 设置探亲
     *
     * @param visitFamily 探亲
     */
    public void setVisitFamily(Double visitFamily) {
        this.visitFamily = visitFamily;
    }

    /**
     * 获取是否有效（0：有效；1：无效）
     *
     * @return del_flag - 是否有效（0：有效；1：无效）
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否有效（0：有效；1：无效）
     *
     * @param delFlag 是否有效（0：有效；1：无效）
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}