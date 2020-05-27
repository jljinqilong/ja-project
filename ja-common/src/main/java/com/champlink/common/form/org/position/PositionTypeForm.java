package com.champlink.common.form.org.position;

import java.util.List;

import com.champlink.common.form.BaseForm;

public class PositionTypeForm extends BaseForm {

	private String typeName;

	private List<Long> staffIdList;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}

}
