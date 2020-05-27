package com.champlink.attendance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.PersonalLeaveMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.PersonalLeave;
import com.champlink.common.form.attendance.PersonalLeaveForm;
import com.champlink.common.vo.PageListVO;

@Service
public class PersonalLeaveService {

	@Autowired
	private PersonalLeaveMapper personalLeaveMapper;

	public int add(PersonalLeave personalLeave) {
		return personalLeaveMapper.insert(personalLeave);
	}

	public int del(Long id) {
		return personalLeaveMapper.deleteByPrimaryKey(id);
	}

	public PersonalLeave get(Long id) {
		PersonalLeave selectByPrimaryKey = personalLeaveMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	public int update(PersonalLeave personalLeave) {
		return personalLeaveMapper.updateByPrimaryKey(personalLeave);
	}

	public PageListVO pageList(PersonalLeaveForm personalLeaveForm) {
		Paginater paginater = Paginater.newInstance(personalLeaveForm);
		PageListVO pageListVO = PageListVO.newInstance(personalLeaveMapper.pageList(paginater));
		return pageListVO;
	}
	
}
