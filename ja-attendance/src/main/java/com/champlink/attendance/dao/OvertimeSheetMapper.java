package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.OvertimeSheet;
import com.github.pagehelper.Page;

@Mapper
public interface OvertimeSheetMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(OvertimeSheet record);

    OvertimeSheet selectByPrimaryKey(Long rowId);

    List<OvertimeSheet> selectAll();

    int updateByPrimaryKey(OvertimeSheet record);

	Page pageList(Paginater paginater);
}