package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.PersonalLeave;
import com.github.pagehelper.Page;

@Mapper
public interface PersonalLeaveMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(PersonalLeave record);

    PersonalLeave selectByPrimaryKey(Long rowId);

    List<PersonalLeave> selectAll();

    int updateByPrimaryKey(PersonalLeave record);

	Page pageList(Paginater paginater);
}