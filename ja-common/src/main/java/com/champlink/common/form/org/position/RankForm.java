package com.champlink.common.form.org.position;

import java.util.List;

import com.champlink.common.form.BaseForm;

public class RankForm extends BaseForm {
/**
 * 职级名称
 */
	private String rankName;
	

	private List<Long> staffIdList;


	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}
	
}
