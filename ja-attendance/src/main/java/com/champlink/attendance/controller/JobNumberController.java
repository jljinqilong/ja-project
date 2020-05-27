package com.champlink.attendance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.attendance.service.JobNumberService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.JobNumber;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.attendance.JobNumberForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/jobNumber")
public class JobNumberController extends BaseController {

	@Autowired
	private JobNumberService jobNumberService;
	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(JobNumber jobNumber) {
		 //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

		jobNumber.setCreatedBy(createdBy);
		jobNumber.setCreatedTime(new Date());
		jobNumberService.add(jobNumber);
		return getSuccessJson();
	}

	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
	    public String del(@PathVariable("id") Long id) {
		jobNumberService.del(id);
	            return getSuccessJson();
	    }

	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String queryOne(@PathVariable("id") Long id) {
		JobNumber jobNumber = jobNumberService.get(id);
		return getSuccessJson(jobNumber);
	}

	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(JobNumber jobNumber) {
		 //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        
		jobNumber.setLastUpdateBy(createdBy);
		jobNumber.setLastUpdateTime(new Date());
		jobNumberService.update(jobNumber);
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/specialList", produces = "text/json;charset=UTF-8")
	public String specialList(JobNumberForm jobNumberForm) {
		if(jobNumberForm.getJobNoName() != null) {
			jobNumberForm.setJobNoName(jobNumberForm.getJobNoName().trim());
		}
		 PageListVO pageListVO = jobNumberService.pageListBySpecial(jobNumberForm);
        return getSuccessJson(pageListVO);
	}
	
	@RequestMapping(value = "/commonList", produces = "text/json;charset=UTF-8")
	public String commonList(JobNumberForm jobNumberForm) {
		if(jobNumberForm.getJobNoName() != null) {
			jobNumberForm.setJobNoName(jobNumberForm.getJobNoName().trim());
		}
		if(jobNumberForm.getTypeId() != null) {
			jobNumberForm.setTypeId(jobNumberForm.getTypeId().trim());
		}
		 PageListVO pageListVO = jobNumberService.pageListByCommon(jobNumberForm);
        return getSuccessJson(pageListVO);
	}

	@RequestMapping(value = "/commonAllList", produces = "text/json;charset=UTF-8")
	public String commonAllList() {
		 List<JobNumber> jobNumberList = jobNumberService.commonAllList();
        return getSuccessJson(jobNumberList);
	}
	
}
