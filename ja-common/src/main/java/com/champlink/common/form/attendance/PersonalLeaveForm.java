package com.champlink.common.form.attendance;

import com.champlink.common.form.BaseForm;

public class PersonalLeaveForm extends BaseForm {

	private String staffId;
	
	private Integer status;
	

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
