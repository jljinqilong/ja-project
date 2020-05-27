package com.champlink.common.form.staff.baseInfo;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;
import com.champlink.common.util.annotations.Validation;

public class ImportAdjustmentWork extends BaseForm {

    /**
     * 员工ID
     */
    private Long staffId;
    /**
     * 工号
     */
    @Excel(title = "工号", isNull = false)
    private String staffNo;

    /**
     * 离职日期
     */
    @Excel(title = "离职日期", isNull = false)
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))|([0-9]{4})/((1[0-2])|(0?[1-9]))/((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "离职日期期格式错误")
    private Date leaveDate;

    /**
     * 离职原因
     */
    @Excel(title = "离职原因")
    private String leaveReason;

    /**
     * 错误描述
     */
    private String errMsg;

    private Long createdBy; //创建人

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}