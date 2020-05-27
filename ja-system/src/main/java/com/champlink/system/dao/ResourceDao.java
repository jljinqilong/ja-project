package com.champlink.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.system.Resource;

@Mapper
public interface ResourceDao {

	int add(Resource record);

	int delById(@Param("rowId") Long rowlId);

	int update(Resource record);

	Resource getById(@Param("rowId") Long rowId);

	Resource getByKey(@Param("key") String key);

	List<Resource> getListByRoleIds(@Param("list") List<Long> list);

	List<Resource> getListByUserId(@Param("userId") Long userId);

	List<Resource> allList();

}
