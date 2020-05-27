package com.champlink.staff.dao.contract;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Award;
import com.github.pagehelper.Page;

@Mapper
public interface AwardDao {

    int add(Award award);

    int delById(Long userId);

    int update(Award award);

    Page<Award> pageList(Paginater paginater);

    Award getById(@Param("rowId") Long rowId);

    int delAllByStaffId(@Param("staffId") Long staffId);
}