package com.champlink.common.form.org.position;

import java.util.List;

import com.champlink.common.form.BaseForm;

public class GradeForm extends BaseForm {

	/**
	 * 岗位赋值名称
	 */
	private String gradeName;
	
	private List<Long> staffIdList;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}

}
