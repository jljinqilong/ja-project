package com.champlink.attendance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.service.MachineService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.attendance.service.call.SystemService;
import com.champlink.common.constant.AttendanceConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.Machine;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.attendance.MachineForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/machine")
public class MachineController extends BaseController {

	@Autowired
	private MachineService machineService;
	@Autowired
	private SystemService systemService;

	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(Machine machine) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);


		machine.setCreatedBy(createdBy);
		machine.setCreatedTime(new Date());
		machineService.add(machine);
		return getSuccessJson();
	}

	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
	    public String del(@PathVariable("id") Long id) {
		 		machineService.del(id);
	            return getSuccessJson();
	    }

	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String queryOne(@PathVariable("id") Long id) {
		Machine machine = machineService.get(id);
		return getSuccessJson(machine);
	}

	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(Machine machine) {
		//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
		machine.setLastUpdateBy(createdBy);
		machine.setLastUpdateTime(new Date());
		machineService.update(machine);
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(MachineForm machineForm) {
		 PageListVO pageListVO = machineService.pageList(machineForm);
		 List<Machine> list = pageListVO.getList();
		 String factoryCategory = systemService.getByTpye(AttendanceConstant.FACTORY_CATEGORY);
		 if (factoryCategory != null) {
	            JSONObject parseObject = JSONObject.parseObject(factoryCategory);
	            if ((Integer) parseObject.get("code") == 200) {
	                List<Code> factoryCategoryList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
	                list = super.translateIdToName(list, factoryCategoryList, new String[] {"factoryId,code,name"});
	            }
	        }
        return getSuccessJson(pageListVO);
	}
	
	@RequestMapping(value = "/allList", produces = "text/json;charset=UTF-8")
	public String allList() {
		List<Machine> allList =  machineService.allList();
        return getSuccessJson(allList);
	}

}
