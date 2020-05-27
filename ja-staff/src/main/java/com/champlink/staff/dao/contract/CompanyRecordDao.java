package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.CompanyRecord;
import com.github.pagehelper.Page;

@Mapper
public interface CompanyRecordDao {

    int add(CompanyRecord companyRecord);

    int delById(@Param("rowId") Long rowId);

    int update(CompanyRecord companyRecord);

    Page<CompanyRecord> pageList(Paginater paginater);

    CompanyRecord getById(@Param("rowId") Long rowId);

}