package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.PositionType;
import com.github.pagehelper.Page;

@Mapper
public interface PositionTypeDao {

	int add(PositionType positiontype);

	int delById(Long userId);

	int update(PositionType positiontype);

	PositionType getById(Long rowId);

	Page<PositionType> pageList(Paginater paginater);

	List<PositionType> AllPositionType();

}
