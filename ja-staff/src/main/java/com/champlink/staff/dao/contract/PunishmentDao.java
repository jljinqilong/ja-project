package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Punishment;
import com.github.pagehelper.Page;

@Mapper
public interface PunishmentDao {

    int add(Punishment punishment);

    int delById(@Param("rowId") Long rowId);

    int update(Punishment punishment);

    Page<Punishment> pageList(Paginater paginater);

    Punishment getById(@Param("rowId") Long rowId);

}