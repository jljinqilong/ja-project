package com.champlink.common.form.attendance;

import com.champlink.common.form.BaseForm;

public class OvertimeSheetForm extends BaseForm {

	private Long baseId;
	
	private Long staffId;
	
	private Long factoryId;

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	
}
