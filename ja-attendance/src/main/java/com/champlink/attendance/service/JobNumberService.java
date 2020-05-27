package com.champlink.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.JobNumberMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.JobNumber;
import com.champlink.common.form.attendance.JobNumberForm;
import com.champlink.common.vo.PageListVO;

@Service
public class JobNumberService {

	@Autowired
	private JobNumberMapper jobNumberMapper;

	public int add(JobNumber jobNumber) {
		int insert = jobNumberMapper.insert(jobNumber);
		return insert;
	}

	public int del(Long id) {
		return jobNumberMapper.deleteByPrimaryKey(id);
	}

	public JobNumber get(Long id) {
		
		return jobNumberMapper.selectByPrimaryKey(id);
	}

	public int update(JobNumber jobNumber) {
		return jobNumberMapper.updateByPrimaryKey(jobNumber);
	}

	/**
	 * 查询特殊班次管理
	 * @param jobNumberForm
	 * @return
	 */
	public PageListVO pageListBySpecial(JobNumberForm jobNumberForm) {
		Paginater paginater = Paginater.newInstance(jobNumberForm);
		PageListVO pageListVO = PageListVO.newInstance(jobNumberMapper.pageListBySpecial(paginater));
		return pageListVO;
	}
	
	/**
	 * 查询普通班次管理
	 * @param jobNumberForm
	 * @return
	 */
	public PageListVO pageListByCommon(JobNumberForm jobNumberForm) {
		Paginater paginater = Paginater.newInstance(jobNumberForm);
		PageListVO pageListVO = PageListVO.newInstance(jobNumberMapper.pageListByCommon(paginater));
		return pageListVO;
	}

	
	public List<JobNumber> commonAllList(){
		return  jobNumberMapper.commonAllList();
	}
	
	
}
