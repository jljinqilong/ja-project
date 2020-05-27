package com.champlink.common.form.attendance;

import com.champlink.common.form.BaseForm;

public class MachineForm extends BaseForm {

	/**
     * 基地ID
     */
    private Long baseId;

    /**
     * 厂别ID
     */
    private Long factoryId;

    /**
     * 序列号
     */
    private Long seq;

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Long getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}
	
}
