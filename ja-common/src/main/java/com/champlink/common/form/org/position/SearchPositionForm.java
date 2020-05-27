package com.champlink.common.form.org.position;

import java.util.List;

import com.champlink.common.form.BaseForm;

public class SearchPositionForm extends BaseForm {

	private Integer positionType;
	private String positionName;
	private String rankName;
	private Integer salaryMax;
	private Integer salaryMin;
	private String postAssignment;
	private String staffSize;
	private String fileDesc;
	private String gradeName;
	private String typeName;
	private List<Long> staffIdList;

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public String getPostAssignment() {
		return postAssignment;
	}

	public void setPostAssignment(String postAssignment) {
		this.postAssignment = postAssignment;
	}

	public String getStaffSize() {
		return staffSize;
	}

	public void setStaffSize(String staffSize) {
		this.staffSize = staffSize;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

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
