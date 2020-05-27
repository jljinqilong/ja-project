package com.champlink.emolument.dao;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltSalary;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EltSalaryDao {
	
	int add(EltSalary eltSalary);

	int delById(Long eltSalaryId);

	int beachDel(@Param("list") Long[] ids);

	int empty();

	int update(EltSalary eltSalary);

	EltSalary getByName(EltSalary eltSalary);

	EltSalary getById(Long rowId);


	Page<EltSalary> pageList(Paginater paginater);
	
}