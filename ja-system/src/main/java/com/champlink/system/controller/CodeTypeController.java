package com.champlink.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.CodeType;
import com.champlink.common.form.system.CodeTypeForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.service.CodeTypeService;

@RequestMapping("/codetype")
@RestController
public class CodeTypeController extends BaseController {

	@Autowired
	private CodeTypeService codeTypeService;

	/**
	 * 分页列表
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
	public String pageList(CodeTypeForm form) {
		PageListVO<CodeType> pageListVO = codeTypeService.pageList(form);
		return getSuccessJson(pageListVO);
	}

	/**
	 * 全部列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allList", produces = "text/json;charset=UTF-8")
	public String allList() {
		List<CodeType> list = codeTypeService.allList();
		return getSuccessJson(list);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
	public String update(CodeType codeType) {
		if (codeTypeService.update(codeType)) {
			return getSuccessJson();
		}
		return getFailJson();
	}

	/**
	 * 查询详细
	 * 
	 * @param rowId
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
	public String getById(@PathVariable("id") Long rowId) {
		CodeType codeType = codeTypeService.getById(rowId);
		return getSuccessJson(codeType);
	}

}
