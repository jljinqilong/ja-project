package com.champlink.staff.dao.contract;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordLang;
import com.github.pagehelper.Page;

@Mapper
public interface RecordLangDao {

    int add(RecordLang recordLang);

    int delById(@Param("rowId") Long rowId);

    int update(RecordLang recordLang);

    Page<RecordLang> pageList(Paginater paginater);

    RecordLang getById(@Param("rowId") Long rowId);

    List<RecordLang> getByStaffId(@Param("staffId") Long staffId);

}