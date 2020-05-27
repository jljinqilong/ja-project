package com.champlink.staff.dao.baseInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.baseInfo.WorkerCodeRule;
import com.github.pagehelper.Page;

@Mapper
public interface WorkerCodeRuleDao {

	int add(WorkerCodeRule workerCodeRule);

	int delByRowId(Long rowId);

	int update(WorkerCodeRule workerCodeRule);

	public Page<WorkerCodeRule> queryAll(Paginater pager);

	public WorkerCodeRule queryOneByBaseId(Long baseId);

	public WorkerCodeRule queryOneByRowId(Long rowId);

	public int updateByDeptId(@Param("deptId") Long deptId, @Param("currentId") int currentId);

	public Integer queryCountByBaseId(@Param("baseId") Long baseId);
}
