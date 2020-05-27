package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Rank;
import com.github.pagehelper.Page;

@Mapper
public interface RankDao {

	int add(Rank rank);

	int delById(Long rowId);

	int update(Rank rank);

	Rank getById(Long rowId);

	Page<Rank> pageList(Paginater paginater);

	List<Rank> getByRankType();

	List<Rank> getRankByGrade(List<Long> positionGrade);

	List<Rank> getAllRank();
	
}