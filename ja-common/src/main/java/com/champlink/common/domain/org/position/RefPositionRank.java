package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class RefPositionRank extends BaseBean {

	private Long rowId;
	private Long positionId;
	private Long rankId;

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getRankId() {
		return rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}


}
