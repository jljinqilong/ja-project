package com.champlink.emolument.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltAccumulationFund;
import com.github.pagehelper.Page;

@Mapper
public interface EltAccumulationFundDao {

    /**
     * 
     * @param eltAccumulationFund
     */
    int add(EltAccumulationFund eltAccumulationFund);

    EltAccumulationFund getByNameAndId(EltAccumulationFund eltAccumulationFund);

    int delById(Long eltAllowanceId);

    int beachDel(@Param("list") Long[] ids);

    int empty();

    EltAccumulationFund getById(Long rowId);

    int update(EltAccumulationFund eltAccumulationFund);

    Page<EltAccumulationFund> pageList(Paginater paginater);
}