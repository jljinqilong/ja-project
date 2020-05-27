package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.BusinessTravel;
import com.github.pagehelper.Page;

@Mapper
public interface BusinessTravelMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(BusinessTravel record);

    BusinessTravel selectByPrimaryKey(Long rowId);

    List<BusinessTravel> selectAll();

    int updateByPrimaryKey(BusinessTravel record);

	Page pageList(Paginater paginater);
}