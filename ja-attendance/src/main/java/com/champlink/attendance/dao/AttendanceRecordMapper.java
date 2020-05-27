package com.champlink.attendance.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.AttendanceRecord;
import com.github.pagehelper.Page;

@Mapper
public interface AttendanceRecordMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(AttendanceRecord record);

    AttendanceRecord selectByPrimaryKey(Long rowId);


    int updateByPrimaryKey(AttendanceRecord record);

	Page pageList(Paginater paginater);
}