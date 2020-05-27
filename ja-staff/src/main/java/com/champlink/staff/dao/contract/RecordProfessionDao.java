package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordProfession;
import com.github.pagehelper.Page;

@Mapper
public interface RecordProfessionDao {

    int add(RecordProfession recordProfession);

    int delById(@Param("rowId") Long rowId);

    int update(RecordProfession recordProfession);

    Page<RecordProfession> pageList(Paginater paginater);

    RecordProfession getById(@Param("rowId") Long rowId);

    int delAllByStaffId(@Param("staffId") Long staffId);
    
}