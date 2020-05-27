package com.champlink.emolument.dao;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltSubsidy;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EltSubsidyDao  {
	
	
	int add(EltSubsidy eltSubsidy);

	int delById(Long rowId);

	int beachDel(@Param("list") Long[] ids);

	int empty();

	int update(EltSubsidy eltSubsidy);

	EltSubsidy getByName(EltSubsidy eltSubsidy);

	EltSubsidy getById(Long rowId);

	Page<EltSubsidy> pageList(Paginater paginater);
	
}