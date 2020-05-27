package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class RefGradeRank extends BaseBean {

	private Long rowId;

	private Long gradeId;

	private Long rankId;

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getRankId() {
		return rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}

}
