package com.champlink.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.CodeType;
import com.github.pagehelper.Page;

@Mapper
public interface CodeTypeDao {

	/**
	 * 更新
	 * 
	 * @param codeType
	 * @return
	 */
	int update(CodeType codeType);

	/**
	 * 根据ID查询详细
	 * 
	 * @param id
	 * @return
	 */
	CodeType getById(Long id);

	/**
	 * 获取全部
	 * 
	 * @return
	 */
	List<CodeType> allList();

	/**
	 * 查询分页列表
	 * 
	 * @param paginater
	 * @return
	 */
	Page<CodeType> pageList(Paginater paginater);
	
	/**
	 * 
	 * @param codeType
	 * @return
	 */
	CodeType getByCodeType(String code);
}
