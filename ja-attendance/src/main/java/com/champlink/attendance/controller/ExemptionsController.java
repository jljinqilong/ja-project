package com.champlink.attendance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.service.ExemptionsService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.Exemptions;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.attendance.ExemptionsForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/exemptions")
public class ExemptionsController extends BaseController {


	@Autowired
	private ExemptionsService exemptionsService;
	@Autowired
	private StaffService staffServisce;
	
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(Exemptions exemptions) {
		exemptions.setCreatedBy(RequestContext.get().getStaffId());
		exemptions.setCreatedTime(new Date());
		exemptionsService.add(exemptions);
		return getSuccessJson();
	}
	
	
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(ExemptionsForm exemptionsForm) {
		 PageListVO<Exemptions> pageListVO = exemptionsService.pageList(exemptionsForm);
		 List<Exemptions> list = pageListVO.getList();	
		 
		 for (Exemptions exemptions : list) {
			 String stfBaseInfo = staffServisce.getStfBaseInfo(exemptions.getStaffId());
			 if (stfBaseInfo != null) {
		            JSONObject parseObject = JSONObject.parseObject(stfBaseInfo);
		            if ((Integer) parseObject.get("code") == 200) {
		            	BaseInfo baseInfo = JSONObject.parseObject(parseObject.getString("data"), BaseInfo.class);
		            	exemptions.setStaffNo(baseInfo.getStaffNo());
		            	exemptions.setStaffName(baseInfo.getStaffName());
		            	exemptions.setDeptName(String.valueOf(baseInfo.getTransNames().get("deptId_baseOrDeptName")));
		            	exemptions.setGradeName(String.valueOf(baseInfo.getTransNames().get("gradeId_gradeName")));
		            	exemptions.setRankName(String.valueOf(baseInfo.getTransNames().get("rankId_rankName")));
		            	exemptions.setPositionName(String.valueOf(baseInfo.getTransNames().get("positionId_positionName")));
		            }
		        }
			 
		}
		 
        return getSuccessJson(pageListVO);
	}
	
	
	@RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
			exemptionsService.del(id);
            return getSuccessJson();
    }
	
}
