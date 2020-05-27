package com.champlink.common.domain;

import com.champlink.common.form.BaseForm;
import com.github.pagehelper.PageHelper;

public class Paginater {

	private Object params;

	private Paginater() {

	}

	public static Paginater newInstance(BaseForm form) {
		Paginater paginater = new Paginater();
		paginater.setParams(form);
		PageHelper.startPage(form.getPageNum(), form.getPageSize(), form.getOrderBy());
		return paginater;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

}
