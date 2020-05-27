package com.champlink.attendance.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.HolidayTypeMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.HolidayType;
import com.champlink.common.form.BaseForm;
import com.champlink.common.vo.PageListVO;
@Service
public class HolidayTypeService {

	@Autowired
	private HolidayTypeMapper holidayTypeDao;

	public int add(HolidayType holidayType) {
		int insert = holidayTypeDao.insert(holidayType);
		return insert;
	}

	public int del(Long id) {
		int deleteByPrimaryKey = holidayTypeDao.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}

	public HolidayType get(Long id) {
		HolidayType selectByPrimaryKey = holidayTypeDao.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	public int update(HolidayType holidayType) {
		int updateByPrimaryKey = holidayTypeDao.updateByPrimaryKey(holidayType);
		return updateByPrimaryKey;
	}

	public PageListVO pageList(BaseForm baseForm) {
		Paginater paginater = Paginater.newInstance(baseForm);
		PageListVO pageListVO = PageListVO.newInstance(holidayTypeDao.pageList(paginater));
		return pageListVO;
	}

	public List<HolidayType> selectAll() {
		List<HolidayType> selectAll = holidayTypeDao.selectAll();
		return selectAll;
	}
	
	
	
}
