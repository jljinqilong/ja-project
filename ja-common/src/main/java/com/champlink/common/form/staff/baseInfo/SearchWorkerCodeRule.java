package com.champlink.common.form.staff.baseInfo;

import com.champlink.common.form.BaseForm;

public class SearchWorkerCodeRule extends BaseForm {

	/**
	 * 基地id
	 */
	private Long baseId;
	
	
	 /**
     * 是否可用  0:可用,1:不可用
     */
    private Integer usable;

	public Integer getUsable() {
		return usable;
	}

	public void setUsable(Integer usable) {
		this.usable = usable;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}
	
	
}
