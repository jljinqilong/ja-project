package com.champlink.staff.dao.contract;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordIt;
import com.github.pagehelper.Page;

@Mapper
public interface RecordItDao {

    int add(RecordIt recordIt);

    int delById(@Param("rowId") Long rowId);

    int update(RecordIt recordIt);

    Page<RecordIt> pageList(Paginater paginater);

    RecordIt getById(@Param("rowId") Long rowId);

    List<RecordIt> getByStaffId(@Param("staffId") Long staffId);

}