package com.champlink.emolument.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltPerformance;
import com.github.pagehelper.Page;

@Mapper
public interface EltPerformanceDao  {
	
	
	Page<EltPerformance> pageList(Paginater paginater);
	
	int insertList(List<EltPerformance> list);
	
}