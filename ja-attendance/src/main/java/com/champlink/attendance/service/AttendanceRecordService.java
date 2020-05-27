package com.champlink.attendance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.attendance.dao.AttendanceRecordMapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.form.attendance.AttendanceRecordForm;
import com.champlink.common.vo.PageListVO;

@Service
public class AttendanceRecordService {

	@Autowired
	private AttendanceRecordMapper attendanceRecordDao;

	public PageListVO pageList(AttendanceRecordForm attendanceRecordForm) {
		Paginater paginater = Paginater.newInstance(attendanceRecordForm);
		PageListVO pageListVO = PageListVO.newInstance(attendanceRecordDao.pageList(paginater));
		return pageListVO;
	}
	
}
