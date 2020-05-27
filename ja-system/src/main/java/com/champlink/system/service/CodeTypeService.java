package com.champlink.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.CodeType;
import com.champlink.common.form.system.CodeTypeForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.dao.CodeTypeDao;

@Service
public class CodeTypeService {

	@Autowired
	private CodeTypeDao codeTypeDao;

	/**
	 * 获取列表
	 * 
	 * @param form
	 * @return
	 */
	public PageListVO<CodeType> pageList(CodeTypeForm form) {
		Paginater paginater = Paginater.newInstance(form);
		PageListVO<CodeType> pageListVO = PageListVO.newInstance(codeTypeDao.pageList(paginater));
		return pageListVO;
	}

	/**
	 * 返回列表
	 * 
	 * @return
	 */
	public List<CodeType> allList() {
		return codeTypeDao.allList();
	}

	/**
	 * 更新
	 * 
	 * @param codeType
	 * @return
	 */
	public boolean update(CodeType codeType) {
		if (codeTypeDao.update(codeType) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 */
	public CodeType getById(Long id) {
		return codeTypeDao.getById(id);
	}

}
