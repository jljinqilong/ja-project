package com.champlink.staff.controller.baseInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.WorkerCodeRule;
import com.champlink.common.form.staff.baseInfo.SearchWorkerCodeRule;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.baseInfo.BaseInfoService;
import com.champlink.staff.service.baseInfo.WorkerCodeRuleService;
@RestController
@RequestMapping("/workerCodeRule")
public class WorkerCodeRuleController extends BaseController {

	@Autowired
	private WorkerCodeRuleService workerCodeRuleService;
	
	@Autowired
	private BaseInfoService baseInfoService;
	
	
	@RequestMapping(value = "/add")
	public String insert(WorkerCodeRule workerCodeRule) {
		
		if(workerCodeRuleService.add(workerCodeRule) > 0) {
			return getSuccessJson();
		}
		
        return getFailJson();		
	}
	
	@RequestMapping(value = "/del")
	public String del(@RequestParam("rowId") Long rowId) {
		WorkerCodeRule queryOneByRowId = workerCodeRuleService.queryOneByRowId(rowId);
		
		int queryByBaseId = baseInfoService.queryByBaseId(Long.valueOf(queryOneByRowId.getBaseId()));
		
		if(queryByBaseId <= 0) {
			if(queryOneByRowId.getCurrentId() == 0) {
				if(workerCodeRuleService.del(rowId) > 0) {
					return getSuccessJson();
				}
			}else {
				AppException.create(200011);
			}
		}else {
			AppException.create(200014); // 工号已经被员工使用,不能删除
		}
		
		
		return getSuccessJson();
	}
	
	@RequestMapping(value = "/update")
	public String update(WorkerCodeRule workerCodeRule) {
		if(workerCodeRuleService.update(workerCodeRule) > 0) {
			return getSuccessJson();
		}
		return getFailJson();
	}
	
	
	@RequestMapping(value = "/list")
	public String queryAll(SearchWorkerCodeRule form) {
		
		PageListVO<WorkerCodeRule> workerCodeRuleList = workerCodeRuleService.getWorkerCodeRuleList(form);
		return getSuccessJson(workerCodeRuleList);
	}
	
	@RequestMapping(value = "/generateWorkerCode/{baseId}")
	public String generateWorkerCode(@PathVariable("baseId") Long baseId, @PathVariable("currentId") int currentId) {
		
		String generateWorkerCode = workerCodeRuleService.generateWorkerCode(baseId, currentId);
		return getSuccessJson(generateWorkerCode);
	}

	
	@RequestMapping(value = "/queryOneByRowId/{row_id}")
	public String queryOneByRowId(@PathVariable("row_id") Long rowId) {
		
	 WorkerCodeRule queryOneByRowId = workerCodeRuleService.queryOneByRowId(rowId);
	 if(queryOneByRowId != null) {
		 return getSuccessJson(queryOneByRowId);
	 }
	 return getFailJson();
	}
}
