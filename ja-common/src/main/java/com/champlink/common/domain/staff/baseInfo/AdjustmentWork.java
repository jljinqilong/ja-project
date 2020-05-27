package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

/**
 * 员工异动
 * 
 * @author natyu
 * @date 2018年7月21日 上午10:53:32
 *
 */
public class AdjustmentWork extends BaseBean {

    /**
     * 员工ID
     */
    private Long staffId;

    /**
     * 变动类型
     */
    private String changeType;

    /**
     * 变动日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date changeDate;

    /**
     * 变动原因/描述
     */
    private String changeReason;

    /**
     * 原基地
     */
    private String originalBase;

    /**
     * 原部门
     */
    private String originalDept;

    /**
     * 原职衔
     */
    private String originalPosition;

    /**
     * 原职等/赋值名称
     */
    private String originalGrade;

    /**
     * 原职级
     */
    private String originalRank;

    /**
     * 新基地
     */
    private String newBase;

    /**
     * 新部门
     */
    private String newDept;

    /**
     * 新职衔
     */
    private String newPosition;

    /**
     * 新职等/赋值名称
     */
    private String newGrade;

    /**
     * 新职级
     */
    private String newRank;

    /**
     * 是否删除
     */
    private Integer delFlag;

    /**
     * 是否调薪
     */
    private String isWageAdjustment;

    /**
     * 借调类型
     */
    private String temporarilyType;

    /**
     * 借调开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date temporarilyStartDate;

    /**
     * 借调结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date temporarilyEndDate;

    /**
     * 外派类型
     */
    private String expatriateType;

    /**
     * 外拍开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date expatriateStartDate;

    /**
     * 外派结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date expatriateEndDate;

    /**
     * 工作内容
     */
    private String jobContent;

    /**
     * 退休类型
     */
    private String retireType;

    /**
     * 退休日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date retireDate;

    /**
     * 退休后管理单位
     */
    private String retireManagementUnit;

    /**
     * 审批单位
     */
    private String examineUnit;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 其他说明
     */
    private String otherExplain;

    /**
     * 离职类型
     */
    private String leaveType;

    /**
     * 离职原因
     */
    private String leaveReason;

    /**
     * 离职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date leaveDate;

    /**
     * 离职后去向
     */
    private String leaveDirection;

    /**
     * 加入黑名单
     */
    private String isBlacklist;

    /**
     * 加入黑名单原因
     */
    private String blacklistReason;

    /**
     * 是否有代通知金
     */
    private String isLieuNoticeWages;

    /**
     * 是否有补偿金
     */
    private String isCompensatoryPayment;

    /**
     * 补偿月数
     */
    private String compensationMonth;

    /**
     * 补偿金额
     */
    private String amountCompensation;

    /**
     * 是否履行竞业限制
     */
    private String isPerformCompetitiveRestriction;

    /**
     * 开始履行时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date startPerformDate;

    /**
     * 结束履行时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endPerformDate;

    /**
     * 是否签订培训协议
     */
    private String isTrainAgreement;

    /**
     * 未满服务期赔偿金额
     */
    private String underServiceCompensate;

    /**
     * 在职状态
     */
    private String jobStatus;
    /**
     * 用工日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 入职前工龄
     */
    private String entryBeforeAge;

    /**
     * 实际结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date realEndTime;

    private String jobTitle;//职称

    private String staffClassify;//员工分类

    /**
     * 原员工工号
     */
    private String originalStaffNo;

    /**
     * 现员工工号
     */
    private String newStaffNo;

    private BaseInfo baseInfo;

    private Integer currentId;

    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    /**
     * 新部门
     */
    private Long newDeptId;

    public Long getNewDeptId() {
        return newDeptId;
    }

    public void setNewDeptId(Long newDeptId) {
        this.newDeptId = newDeptId;
    }

    public String getOriginalStaffNo() {
        return originalStaffNo;
    }

    public void setOriginalStaffNo(String originalStaffNo) {
        this.originalStaffNo = originalStaffNo;
    }

    public String getNewStaffNo() {
        return newStaffNo;
    }

    public void setNewStaffNo(String newStaffNo) {
        this.newStaffNo = newStaffNo;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStaffClassify() {
        return staffClassify;
    }

    public void setStaffClassify(String staffClassify) {
        this.staffClassify = staffClassify;
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
     * 获取变动类型
     *
     * @return change_type - 变动类型
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * 设置变动类型
     *
     * @param changeType 变动类型
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * 获取变动日期
     *
     * @return change_date - 变动日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置变动日期
     *
     * @param changeDate 变动日期
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 获取变动原因/描述
     *
     * @return change_reason - 变动原因/描述
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * 设置变动原因/描述
     *
     * @param changeReason 变动原因/描述
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * 获取原基地
     *
     * @return original_base - 原基地
     */
    public String getOriginalBase() {
        return originalBase;
    }

    /**
     * 设置原基地
     *
     * @param originalBase 原基地
     */
    public void setOriginalBase(String originalBase) {
        this.originalBase = originalBase;
    }

    /**
     * 获取原部门
     *
     * @return original_dapt - 原部门
     */
    public String getOriginalDept() {
        return originalDept;
    }

    /**
     * 设置原部门
     *
     * @param originalDept 原部门
     */
    public void setOriginalDept(String originalDept) {
        this.originalDept = originalDept;
    }

    /**
     * 获取原职衔
     *
     * @return original_position - 原职衔
     */
    public String getOriginalPosition() {
        return originalPosition;
    }

    /**
     * 设置原职衔
     *
     * @param originalPosition 原职衔
     */
    public void setOriginalPosition(String originalPosition) {
        this.originalPosition = originalPosition;
    }

    /**
     * 获取原职等/赋值名称
     *
     * @return original_grade - 原职等/赋值名称
     */
    public String getOriginalGrade() {
        return originalGrade;
    }

    /**
     * 设置原职等/赋值名称
     *
     * @param originalGrade 原职等/赋值名称
     */
    public void setOriginalGrade(String originalGrade) {
        this.originalGrade = originalGrade;
    }

    /**
     * 获取原职级
     *
     * @return original_rank - 原职级
     */
    public String getOriginalRank() {
        return originalRank;
    }

    /**
     * 设置原职级
     *
     * @param originalRank 原职级
     */
    public void setOriginalRank(String originalRank) {
        this.originalRank = originalRank;
    }

    /**
     * 获取新基地
     *
     * @return new_base - 新基地
     */
    public String getNewBase() {
        return newBase;
    }

    /**
     * 设置新基地
     *
     * @param newBase 新基地
     */
    public void setNewBase(String newBase) {
        this.newBase = newBase;
    }

    /**
     * 获取新部门
     *
     * @return new_dept - 新部门
     */
    public String getNewDept() {
        return newDept;
    }

    /**
     * 设置新部门
     *
     * @param newDept 新部门
     */
    public void setNewDept(String newDept) {
        this.newDept = newDept;
    }

    /**
     * 获取新职衔
     *
     * @return new_position - 新职衔
     */
    public String getNewPosition() {
        return newPosition;
    }

    /**
     * 设置新职衔
     *
     * @param newPosition 新职衔
     */
    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }

    /**
     * 获取新职等/赋值名称
     *
     * @return new_grade - 新职等/赋值名称
     */
    public String getNewGrade() {
        return newGrade;
    }

    /**
     * 设置新职等/赋值名称
     *
     * @param newGrade 新职等/赋值名称
     */
    public void setNewGrade(String newGrade) {
        this.newGrade = newGrade;
    }

    /**
     * 获取新职级
     *
     * @return new_rank - 新职级
     */
    public String getNewRank() {
        return newRank;
    }

    /**
     * 设置新职级
     *
     * @param newRank 新职级
     */
    public void setNewRank(String newRank) {
        this.newRank = newRank;
    }

    /**
     * 获取是否删除
     *
     * @return del_flag - 是否删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否删除
     *
     * @param delFlag 是否删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取是否调薪
     *
     * @return is_wage_adjustment - 是否调薪
     */
    public String getIsWageAdjustment() {
        return isWageAdjustment;
    }

    /**
     * 设置是否调薪
     *
     * @param isWageAdjustment 是否调薪
     */
    public void setIsWageAdjustment(String isWageAdjustment) {
        this.isWageAdjustment = isWageAdjustment;
    }

    /**
     * 获取借调类型
     *
     * @return temporarily_type - 借调类型
     */
    public String getTemporarilyType() {
        return temporarilyType;
    }

    /**
     * 设置借调类型
     *
     * @param temporarilyType 借调类型
     */
    public void setTemporarilyType(String temporarilyType) {
        this.temporarilyType = temporarilyType;
    }

    /**
     * 获取借调开始日期
     *
     * @return temporarily_start_date - 借调开始日期
     */
    public Date getTemporarilyStartDate() {
        return temporarilyStartDate;
    }

    /**
     * 设置借调开始日期
     *
     * @param temporarilyStartDate 借调开始日期
     */
    public void setTemporarilyStartDate(Date temporarilyStartDate) {
        this.temporarilyStartDate = temporarilyStartDate;
    }

    /**
     * 获取借调结束日期
     *
     * @return temporarily_end_date - 借调结束日期
     */
    public Date getTemporarilyEndDate() {
        return temporarilyEndDate;
    }

    /**
     * 设置借调结束日期
     *
     * @param temporarilyEndDate 借调结束日期
     */
    public void setTemporarilyEndDate(Date temporarilyEndDate) {
        this.temporarilyEndDate = temporarilyEndDate;
    }

    /**
     * 获取外派类型
     *
     * @return expatriate_type - 外派类型
     */
    public String getExpatriateType() {
        return expatriateType;
    }

    /**
     * 设置外派类型
     *
     * @param expatriateType 外派类型
     */
    public void setExpatriateType(String expatriateType) {
        this.expatriateType = expatriateType;
    }

    /**
     * 获取外派开始日期
     *
     * @return expatriate_end_date - 外派开始日期
     */
    public Date getExpatriateStartDate() {
        return expatriateStartDate;
    }

    /**
     * 设置外拍开始日期
     *
     * @param expatriateStartDate 外拍开始日期
     */
    public void setExpatriateStartDate(Date expatriateStartDate) {
        this.expatriateStartDate = expatriateStartDate;
    }

    /**
     * 获取外派结束日期
     *
     * @return expatriate_end_date - 外派结束日期
     */
    public Date getExpatriateEndDate() {
        return expatriateEndDate;
    }

    /**
     * 设置外派结束日期
     *
     * @param expatriateEndDate 外派结束日期
     */
    public void setExpatriateEndDate(Date expatriateEndDate) {
        this.expatriateEndDate = expatriateEndDate;
    }

    /**
     * 获取工作内容
     *
     * @return job_content - 工作内容
     */
    public String getJobContent() {
        return jobContent;
    }

    /**
     * 设置工作内容
     *
     * @param jobContent 工作内容
     */
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    /**
     * 获取退休类型
     *
     * @return retire_type - 退休类型
     */
    public String getRetireType() {
        return retireType;
    }

    /**
     * 设置退休类型
     *
     * @param retireType 退休类型
     */
    public void setRetireType(String retireType) {
        this.retireType = retireType;
    }

    /**
     * 获取退休日期
     *
     * @return retire_date - 退休日期
     */
    public Date getRetireDate() {
        return retireDate;
    }

    /**
     * 设置退休日期
     *
     * @param retireDate 退休日期
     */
    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    /**
     * 获取退休后管理单位
     *
     * @return retire_management_unit - 退休后管理单位
     */
    public String getRetireManagementUnit() {
        return retireManagementUnit;
    }

    /**
     * 设置退休后管理单位
     *
     * @param retireManagementUnit 退休后管理单位
     */
    public void setRetireManagementUnit(String retireManagementUnit) {
        this.retireManagementUnit = retireManagementUnit;
    }

    /**
     * 获取审批单位
     *
     * @return examine_unit - 审批单位
     */
    public String getExamineUnit() {
        return examineUnit;
    }

    /**
     * 设置审批单位
     *
     * @param examineUnit 审批单位
     */
    public void setExamineUnit(String examineUnit) {
        this.examineUnit = examineUnit;
    }

    /**
     * 获取批准文号
     *
     * @return approval_number - 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 设置批准文号
     *
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    /**
     * 获取其他说明
     *
     * @return other_explain - 其他说明
     */
    public String getOtherExplain() {
        return otherExplain;
    }

    /**
     * 设置其他说明
     *
     * @param otherExplain 其他说明
     */
    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain;
    }

    /**
     * 获取离职类型
     *
     * @return leave_type - 离职类型
     */
    public String getLeaveType() {
        return leaveType;
    }

    /**
     * 设置离职类型
     *
     * @param leaveType 离职类型
     */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
     * 获取离职原因
     *
     * @return leave_reason - 离职原因
     */
    public String getLeaveReason() {
        return leaveReason;
    }

    /**
     * 设置离职原因
     *
     * @param leaveReason 离职原因
     */
    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    /**
     * 获取离职日期
     *
     * @return leave_date - 离职日期
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * 设置离职日期
     *
     * @param leaveDate 离职日期
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * 获取离职后去向
     *
     * @return leave_direction - 离职后去向
     */
    public String getLeaveDirection() {
        return leaveDirection;
    }

    /**
     * 设置离职后去向
     *
     * @param leaveDirection 离职后去向
     */
    public void setLeaveDirection(String leaveDirection) {
        this.leaveDirection = leaveDirection;
    }

    /**
     * 获取加入黑名单
     *
     * @return is_blacklist - 加入黑名单
     */
    public String getIsBlacklist() {
        return isBlacklist;
    }

    /**
     * 设置加入黑名单
     *
     * @param isBlacklist 加入黑名单
     */
    public void setIsBlacklist(String isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    /**
     * 获取加入黑名单原因
     *
     * @return blacklist_reason - 加入黑名单原因
     */
    public String getBlacklistReason() {
        return blacklistReason;
    }

    /**
     * 设置加入黑名单原因
     *
     * @param blacklistReason 加入黑名单原因
     */
    public void setBlacklistReason(String blacklistReason) {
        this.blacklistReason = blacklistReason;
    }

    /**
     * 获取是否有代通知金
     *
     * @return is_lieu_notice_wages - 是否有代通知金
     */
    public String getIsLieuNoticeWages() {
        return isLieuNoticeWages;
    }

    /**
     * 设置是否有代通知金
     *
     * @param isLieuNoticeWages 是否有代通知金
     */
    public void setIsLieuNoticeWages(String isLieuNoticeWages) {
        this.isLieuNoticeWages = isLieuNoticeWages;
    }

    /**
     * 获取是否有补偿金
     *
     * @return is_compensatory_payment - 是否有补偿金
     */
    public String getIsCompensatoryPayment() {
        return isCompensatoryPayment;
    }

    /**
     * 设置是否有补偿金
     *
     * @param isCompensatoryPayment 是否有补偿金
     */
    public void setIsCompensatoryPayment(String isCompensatoryPayment) {
        this.isCompensatoryPayment = isCompensatoryPayment;
    }

    /**
     * 获取补偿月数
     *
     * @return compensation_month - 补偿月数
     */
    public String getCompensationMonth() {
        return compensationMonth;
    }

    /**
     * 设置补偿月数
     *
     * @param compensationMonth 补偿月数
     */
    public void setCompensationMonth(String compensationMonth) {
        this.compensationMonth = compensationMonth;
    }

    /**
     * 获取补偿金额
     *
     * @return amount_compensation - 补偿金额
     */
    public String getAmountCompensation() {
        return amountCompensation;
    }

    /**
     * 设置补偿金额
     *
     * @param amountCompensation 补偿金额
     */
    public void setAmountCompensation(String amountCompensation) {
        this.amountCompensation = amountCompensation;
    }

    /**
     * 获取是否履行竞业限制
     *
     * @return is_perform_competitive_restriction - 是否履行竞业限制
     */
    public String getIsPerformCompetitiveRestriction() {
        return isPerformCompetitiveRestriction;
    }

    /**
     * 设置是否履行竞业限制
     *
     * @param isPerformCompetitiveRestriction 是否履行竞业限制
     */
    public void setIsPerformCompetitiveRestriction(String isPerformCompetitiveRestriction) {
        this.isPerformCompetitiveRestriction = isPerformCompetitiveRestriction;
    }

    /**
     * 获取开始履行时间
     *
     * @return start_perform_date - 开始履行时间
     */
    public Date getStartPerformDate() {
        return startPerformDate;
    }

    /**
     * 设置开始履行时间
     *
     * @param startPerformDate 开始履行时间
     */
    public void setStartPerformDate(Date startPerformDate) {
        this.startPerformDate = startPerformDate;
    }

    /**
     * 获取结束履行时间
     *
     * @return end_perform_date - 结束履行时间
     */
    public Date getEndPerformDate() {
        return endPerformDate;
    }

    /**
     * 设置结束履行时间
     *
     * @param endPerformDate 结束履行时间
     */
    public void setEndPerformDate(Date endPerformDate) {
        this.endPerformDate = endPerformDate;
    }

    /**
     * 获取是否签订培训协议
     *
     * @return is_train_agreement - 是否签订培训协议
     */
    public String getIsTrainAgreement() {
        return isTrainAgreement;
    }

    /**
     * 设置是否签订培训协议
     *
     * @param isTrainAgreement 是否签订培训协议
     */
    public void setIsTrainAgreement(String isTrainAgreement) {
        this.isTrainAgreement = isTrainAgreement;
    }

    /**
     * 获取未满服务期赔偿金额
     *
     * @return under_service_compensate - 未满服务期赔偿金额
     */
    public String getUnderServiceCompensate() {
        return underServiceCompensate;
    }

    /**
     * 设置未满服务期赔偿金额
     *
     * @param underServiceCompensate 未满服务期赔偿金额
     */
    public void setUnderServiceCompensate(String underServiceCompensate) {
        this.underServiceCompensate = underServiceCompensate;
    }

    /**
     * 获取在职状态
     *
     * @return job_status - 在职状态
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置在职状态
     *
     * @param job_status 在职状态
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 获取用工类型
     *
     * @return work_date - 用工类型
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 设置用工类型
     *
     * @param workDate 用工类型
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取入职前工龄
     *
     * @return entry_before_age - 入职前工龄
     */
    public String getEntryBeforeAge() {
        return entryBeforeAge;
    }

    /**
     * 设置入职前工龄
     *
     * @param entryBeforeAge 入职前工龄
     */
    public void setEntryBeforeAge(String entryBeforeAge) {
        this.entryBeforeAge = entryBeforeAge;
    }

    public Date getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(Date realEndTime) {
        this.realEndTime = realEndTime;
    }
}