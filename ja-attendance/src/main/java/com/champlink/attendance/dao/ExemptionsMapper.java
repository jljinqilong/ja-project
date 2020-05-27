package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.Exemptions;
import com.github.pagehelper.Page;

@Mapper
public interface ExemptionsMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(Exemptions record);

    Exemptions selectByPrimaryKey(Long rowId);

    List<Exemptions> selectAll();

    int updateByPrimaryKey(Exemptions record);

	Page<Exemptions> pageList(Paginater paginater);
}