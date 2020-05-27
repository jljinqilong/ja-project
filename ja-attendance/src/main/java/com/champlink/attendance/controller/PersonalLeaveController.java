package com.champlink.attendance.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.attendance.service.PersonalLeaveService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.PersonalLeave;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.attendance.PersonalLeaveForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/personalLeave")
public class PersonalLeaveController extends BaseController {

	@Autowired
	private PersonalLeaveService personalLeaveService;
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(PersonalLeave personalLeave) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        personalLeave.setCreatedBy(createdBy);
		personalLeave.setCreatedTime(new Date());
		personalLeaveService.add(personalLeave);
		return getSuccessJson();
	}

	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
	    public String del(@PathVariable("id") Long id) {
		personalLeaveService.del(id);
	            return getSuccessJson();
	    }

	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String queryOne(@PathVariable("id") Long id) {
		PersonalLeave personalLeave = personalLeaveService.get(id);
		return getSuccessJson(personalLeave);
	}

	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(PersonalLeave personalLeave) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
		personalLeave.setLastUpdateBy(createdBy);
		personalLeave.setLastUpdateTime(new Date());
		personalLeaveService.update(personalLeave);
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(PersonalLeaveForm personalLeaveForm) {
		if("".equals(personalLeaveForm.getStaffId())) {
			personalLeaveForm.setStaffId(null);
		}
		 PageListVO pageListVO = personalLeaveService.pageList(personalLeaveForm);
		 
         return getSuccessJson(pageListVO);
	}
	
}
