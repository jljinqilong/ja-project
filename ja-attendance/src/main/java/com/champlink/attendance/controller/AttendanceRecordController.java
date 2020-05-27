package com.champlink.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.attendance.service.AttendanceRecordService;
import com.champlink.common.controller.BaseController;
import com.champlink.common.form.attendance.AttendanceRecordForm;
import com.champlink.common.vo.PageListVO;

@RestController
@RequestMapping("/record")
public class AttendanceRecordController extends BaseController {

	@Autowired
	private AttendanceRecordService attendanceRecordService;

    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(AttendanceRecordForm attendanceRecordForm) {
        PageListVO pageListVO = attendanceRecordService.pageList(attendanceRecordForm);

        return getSuccessJson(pageListVO);
    }
	
}
