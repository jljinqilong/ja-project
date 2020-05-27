package com.champlink.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.dao.JobNumberMapper;
import com.champlink.attendance.dao.OvertimeSheetMapper;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.JobNumber;
import com.champlink.common.domain.attendance.OvertimeSheet;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.attendance.OvertimeSheetForm;
import com.champlink.common.vo.PageListVO;

@Service
public class OvertimeSheetService {

	@Autowired
	private OvertimeSheetMapper overtimeSheetDao;
	@Autowired
	private JobNumberMapper jobNumberMapper;
	@Autowired
	private StaffService staffService;
	
	@Transactional	
	public int add(OvertimeSheet overtimeSheet) {
		if(overtimeSheet.getJobNoId() != null) {
			JobNumber jobNumber = jobNumberMapper.selectByPrimaryKey(overtimeSheet.getJobNoId());
			overtimeSheet.setJobNoName(jobNumber.getJobNoName());
		}
		
		 String stfBaseInfo = staffService.getStfBaseInfo(overtimeSheet.getStaffId());
		 if (stfBaseInfo != null) {
	            JSONObject parseObject = JSONObject.parseObject(stfBaseInfo);
	            if ((Integer) parseObject.get("code") == 200) {
	            	BaseInfo baseInfo = JSONObject.parseObject(parseObject.getString("data"), BaseInfo.class);
	            	
					overtimeSheet.setBaseName(String.valueOf(baseInfo.getTransNames().get("baseId_baseOrDeptName")));
					overtimeSheet.setStaffNo(baseInfo.getStaffNo());// 员工编号
					overtimeSheet.setStaffName(baseInfo.getStaffName());
					overtimeSheet.setDeptName(String.valueOf(baseInfo.getTransNames().get("deptId_baseOrDeptName")));
				}
			}
		
		int insert = overtimeSheetDao.insert(overtimeSheet);
		return insert;
	}

	public int del(Long id) {
		return overtimeSheetDao.deleteByPrimaryKey(id);
	}

	public OvertimeSheet get(Long id) {
		OvertimeSheet selectByPrimaryKey = overtimeSheetDao.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	public int update(OvertimeSheet overtimeSheet) {
		return overtimeSheetDao.updateByPrimaryKey(overtimeSheet);
	}

	public PageListVO pageList(OvertimeSheetForm overtimeSheetForm) {
		Paginater paginater = Paginater.newInstance(overtimeSheetForm);
		PageListVO pageListVO = PageListVO.newInstance(overtimeSheetDao.pageList(paginater));
		return pageListVO;
	}
	
}
