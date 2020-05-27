package com.champlink.attendance.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.attendance.service.OvertimeSheetService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.OvertimeSheet;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.attendance.OvertimeSheetForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/overtimeSheet")
public class OvertimeSheetController extends BaseController {


	@Autowired
	private OvertimeSheetService overtimeSheetService;
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(OvertimeSheet overtimeSheet) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
		overtimeSheet.setCreatedBy(createdBy);
		overtimeSheet.setCreatedTime(new Date());
		overtimeSheetService.add(overtimeSheet);
		return getSuccessJson();
	}

	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
	    public String del(@PathVariable("id") Long id) {
		overtimeSheetService.del(id);
        return getSuccessJson();
	    }

	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String queryOne(@PathVariable("id") Long id) {
		OvertimeSheet overtimeSheet = overtimeSheetService.get(id);
		return getSuccessJson(overtimeSheet);
	}

	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(OvertimeSheet overtimeSheet) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
		overtimeSheet.setLastUpdateBy(createdBy);
		overtimeSheet.setLastUpdateTime(new Date());
		overtimeSheetService.update(overtimeSheet);
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(OvertimeSheetForm overtimeSheetForm) {
		 PageListVO pageListVO = overtimeSheetService.pageList(overtimeSheetForm);
        return getSuccessJson(pageListVO);
	}
	
	
}
