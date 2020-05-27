package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.ForeignVisa;
import com.github.pagehelper.Page;

@Mapper
public interface ForeignVisaDao {

    int add(ForeignVisa foreignVisa);

    int delById(@Param("rowId") Long rowId);

    int update(ForeignVisa foreignVisa);

    Page<ForeignVisa> pageList(Paginater paginater);

    ForeignVisa getById(@Param("rowId") Long rowId);

}