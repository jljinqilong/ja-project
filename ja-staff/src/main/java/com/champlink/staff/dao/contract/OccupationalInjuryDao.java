package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.OccupationalInjury;
import com.github.pagehelper.Page;

@Mapper
public interface OccupationalInjuryDao {

    int add(OccupationalInjury occupationalInjury);

    int delById(@Param("rowId") Long rowId);

    int update(OccupationalInjury occupationalInjury);

    Page<OccupationalInjury> pageList(Paginater paginater);

    OccupationalInjury getById(@Param("rowId") Long rowId);

}