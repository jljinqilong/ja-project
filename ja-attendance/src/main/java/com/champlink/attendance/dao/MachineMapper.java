package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.Machine;
import com.github.pagehelper.Page;

@Mapper
public interface MachineMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(Machine record);

    Machine selectByPrimaryKey(Long rowId);

    List<Machine> selectAll();

    int updateByPrimaryKey(Machine record);

	Page pageList(Paginater paginater);
	
	int getCountByMachineName(String machineName);
	
	Machine getMachineByMachineName(String machineName);
	
}