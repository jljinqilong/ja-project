package com.champlink.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.system.RefUserResource;

@Mapper
public interface RefUserResourceDao {

	int addBatch(@Param("list") List<RefUserResource> list);

	int delByUid(@Param("userId") Long userId);

}
