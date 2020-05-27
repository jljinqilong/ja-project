package com.champlink.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.system.RefRoleResource;

@Mapper
public interface RefRoleResourceDao {

	int addBatch(@Param("list") List<RefRoleResource> list);

	int delByRoleId(@Param("roleId") Long roleId);

}
