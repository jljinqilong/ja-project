package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.ForeignReside;
import com.github.pagehelper.Page;

@Mapper
public interface ForeignResideDao {

    int add(ForeignReside foreignReside);

    int delById(@Param("rowId") Long rowId);

    int update(ForeignReside foreignReside);

    Page<ForeignReside> pageList(Paginater paginater);

    ForeignReside getById(@Param("rowId") Long rowId);

}