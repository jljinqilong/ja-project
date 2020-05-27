package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.JobNumber;
import com.github.pagehelper.Page;

@Mapper
public interface JobNumberMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(JobNumber record);

    JobNumber selectByPrimaryKey(Long rowId);

    List<JobNumber> selectAll();

    int updateByPrimaryKey(JobNumber record);

	Page pageListBySpecial(Paginater paginater);
	
	Page pageListByCommon(Paginater paginater);
	
	List<JobNumber> commonAllList();
	
}