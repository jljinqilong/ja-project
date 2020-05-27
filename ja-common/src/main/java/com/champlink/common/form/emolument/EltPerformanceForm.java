package com.champlink.common.form.emolument;

import com.champlink.common.form.BaseForm;

public class EltPerformanceForm extends BaseForm {

	
	private Long baseId;
	
	private String staffNo;
	
	private Float amountOfPerformance;

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Float getAmountOfPerformance() {
		return amountOfPerformance;
	}

	public void setAmountOfPerformance(Float amountOfPerformance) {
		this.amountOfPerformance = amountOfPerformance;
	}
	
	
}
