package com.champlink.emolument.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltAllowance;
import com.github.pagehelper.Page;

@Mapper
public interface EltAllowanceDao {
    int add(EltAllowance eltAllowance);

    int delById(Long eltAllowanceId);

    int beachDel(@Param("list") Long[] ids);

    int empty();

    int update(EltAllowance eltAllowance);

    EltAllowance getByName(EltAllowance eltAllowance);

    EltAllowance getById(Long rowId);

    Page<EltAllowance> pageList(Paginater paginater);

}