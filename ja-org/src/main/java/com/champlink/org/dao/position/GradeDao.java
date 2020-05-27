package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Grade;
import com.github.pagehelper.Page;

@Mapper
public interface GradeDao {

	int add(Grade grade);

	int delById(Long userId);

	int update(Grade grade);

	Grade getById(Long rowId);

	Page<Grade> pageList(Paginater paginater);

	List<Grade> getByType();

	List<Grade> getAllGrade();
	
}