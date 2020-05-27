package com.champlink.org.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.org.OrgAllBackup;


@Mapper
public interface OrgAllBackupDao  {
	
	int add(@Param("list") List<OrgAllBackup> list);
	
	int deleteByBackUpTime(Date backUpTime);
	
	List<OrgAllBackup> findByBackUpTime(Date backUpTime);

	List<OrgAllBackup> findByBackUpTimeAndDeptIdList(@Param("backUpTime") Date backUpTime, @Param("deptIdList") List<Long> deptIdList);
	
	OrgAllBackup findOneByRowId(@Param("backUpTime") Date backUpTime, @Param("rowId") Long rowId);
	
	OrgAllBackup findOneByParentId(@Param("backUpTime") Date backUpTime, @Param("parentId") Long parentId);
}