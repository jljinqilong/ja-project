package com.champlink.attendance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.HolidayType;
import com.github.pagehelper.Page;

@Mapper
public interface HolidayTypeMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(HolidayType record);

    HolidayType selectByPrimaryKey(Long rowId);

    List<HolidayType> selectAll();

    int updateByPrimaryKey(HolidayType record);

	Page pageList(Paginater paginater);
}