package com.champlink.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.ExemptionsMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.Exemptions;
import com.champlink.common.form.attendance.ExemptionsForm;
import com.champlink.common.vo.PageListVO;

@Service
public class ExemptionsService {
	
	@Autowired
	private ExemptionsMapper exemptionsMapper;
	
	
	public int add(Exemptions exemptions) {
		return exemptionsMapper.insert(exemptions);
	}

	public PageListVO<Exemptions> pageList(ExemptionsForm exemptionsForm) {
		Paginater paginater = Paginater.newInstance(exemptionsForm);
		PageListVO<Exemptions> pageListVO = PageListVO.newInstance(exemptionsMapper.pageList(paginater));
		return pageListVO;
	}

	
	
	public int del(Long id) {
		return exemptionsMapper.deleteByPrimaryKey(id);
	}
	
	
	
	
}
