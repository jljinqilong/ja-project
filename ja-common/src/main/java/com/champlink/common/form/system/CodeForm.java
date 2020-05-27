package com.champlink.common.form.system;

import com.champlink.common.form.BaseForm;

public class CodeForm extends BaseForm {

	private String code;
	private String name;
	private Long typeId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

}
