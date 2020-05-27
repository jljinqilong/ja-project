package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Disability;
import com.github.pagehelper.Page;

@Mapper
public interface DisabilityDao {

    int add(Disability disability);

    int delById(@Param("rowId") Long rowId);

    int update(Disability disability);

    Page<Disability> pageList(Paginater paginater);

    Disability getById(@Param("rowId") Long rowId);

}