package com.champlink.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.BusinessTravelMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.BusinessTravel;
import com.champlink.common.form.attendance.BusinessTravelForm;
import com.champlink.common.vo.PageListVO;

@Service
public class BusinessTravelService {

	@Autowired
	private BusinessTravelMapper businessTravelDao;

	public int add(BusinessTravel businessTravel) {
		return businessTravelDao.insert(businessTravel);
	}

	public int del(Long id) {
		// TODO Auto-generated method stub
		return businessTravelDao.deleteByPrimaryKey(id);
	}

	public BusinessTravel get(Long id) {
		return businessTravelDao.selectByPrimaryKey(id);
	}

	public int update(BusinessTravel businessTravel) {
		return businessTravelDao.updateByPrimaryKey(businessTravel);
	}

	public PageListVO pageList(BusinessTravelForm businessTravelForm) {
		Paginater paginater = Paginater.newInstance(businessTravelForm);
		PageListVO pageListVO = PageListVO.newInstance(businessTravelDao.pageList(paginater));
		return pageListVO;
	}
	
	
	
}
