package com.champlink.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.OrgInfo;

@Mapper
public interface OrgInfoDao  {
	
	int add(OrgInfo orgInfo);
	
	int delByOrgId(Long orgId);
	
	int updateByOrgId(OrgInfo orgInfo);
	
	OrgInfo findOneByOrgId(Long orgId);
	
	List<OrgInfo> allList();
	
	List<OrgInfo> findListByOrgIds(@Param("ids") List<Long> ids);
}	