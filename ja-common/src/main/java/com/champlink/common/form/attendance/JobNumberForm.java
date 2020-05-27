package com.champlink.common.form.attendance;

import com.champlink.common.form.BaseForm;

public class JobNumberForm extends BaseForm {

	 /**
     * 班次名称
     */
    private String jobNoName;

    /**
     * 班次类型
     */
    private String typeId;

	public String getJobNoName() {
		return jobNoName;
	}

	public void setJobNoName(String jobNoName) {
		this.jobNoName = jobNoName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
