package com.champlink.attendance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.service.HolidayTypeService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.attendance.service.call.SystemService;
import com.champlink.common.constant.AttendanceConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.HolidayType;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.BaseForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
@RestController
@RequestMapping("/holidayType")
public class HolidayTypeController extends BaseController {


	@Autowired
	private HolidayTypeService holidayTypeService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(HolidayType holidayType) {
	      //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        
		holidayType.setCreatedBy(createdBy);
		holidayType.setCreatedTime(new Date());
		holidayTypeService.add(holidayType);
		return getSuccessJson();
	}

	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
	    public String del(@PathVariable("id") Long id) {
		holidayTypeService.del(id);
        return getSuccessJson();
	    }

	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String queryOne(@PathVariable("id") Long id) {
		HolidayType holidayType = holidayTypeService.get(id);
		return getSuccessJson(holidayType);
	}

	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(HolidayType holidayType) {
		 //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
		holidayType.setLastUpdateBy(createdBy);
		
		holidayType.setLastUpdateTime(new Date());
		holidayTypeService.update(holidayType);
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(BaseForm baseForm) {
		 PageListVO pageListVO = holidayTypeService.pageList(baseForm);
		 List<HolidayType> list = pageListVO.getList();
		 String timeTypeCategory = systemService.getByTpye(AttendanceConstant.TIME_TYPE);
		 if (timeTypeCategory != null) {
	            JSONObject parseObject = JSONObject.parseObject(timeTypeCategory);
	            if ((Integer) parseObject.get("code") == 200) {
	                List<Code> factoryCategoryList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
	                list = super.translateIdToName(list, factoryCategoryList, new String[] {"unit,code,name"});
	            }
	        }
        return getSuccessJson(pageListVO);
	}
	
	
	@RequestMapping(value = "/selectAll", produces = "text/json;charset=UTF-8")
	public String selectAll() {
		
		List<HolidayType> holidayTypeList = holidayTypeService.selectAll();
        return getSuccessJson(holidayTypeList);
	}
	
	
	
}
