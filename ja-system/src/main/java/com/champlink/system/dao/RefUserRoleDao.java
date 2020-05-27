package com.champlink.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.system.RefUserRole;

@Mapper
public interface RefUserRoleDao {

	int delByUid(@Param("userId") Long userId);

	int addBatch(@Param("list") List<RefUserRole> list);

}
