package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportEltPerformanceForm extends BaseForm {

	/**
	 * 员工工号
	 */
	@Excel(title = "工号", isNull = false)
	private String staffNo;
	
	 /**
     * 月份
     */
	@Excel(title = "月份", isNull = false)
    private Integer month;

    /**
     * 绩效金额
     */
	@Excel(title = "绩效金额", isNull = false)
    private Float amountOfPerformance;

	
    private String errMsg;
    
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Float getAmountOfPerformance() {
		return amountOfPerformance;
	}

	public void setAmountOfPerformance(Float amountOfPerformance) {
		this.amountOfPerformance = amountOfPerformance;
	}

	
}
