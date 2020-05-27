package com.champlink.common.form.attendance;

import java.util.Date;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportCreditCardLogForm extends BaseForm {

	/**
	 * 员工工号
	 */
	@Excel(title = "工号", isNull = false)
	private String staffNo;

	/**
	 * 状态：0：有效，1：无效
	 */
	@Excel(title = "状态", isNull = false)
	private Integer status;

	/**
	 * 考勤机id
	 */
	@Excel(title = "考勤机名称", isNull = false)
	private String machineName;

	/**
	 * 刷卡时间
	 */
	@Excel(title = "刷卡时间", isNull = false)
	private Date time;

	/**
	 * 错误描述
	 */
	private String errMsg;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
