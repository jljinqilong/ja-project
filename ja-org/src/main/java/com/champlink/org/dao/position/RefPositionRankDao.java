package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.org.position.RefPositionRank;

@Mapper
public interface RefPositionRankDao {
	int addPosition(RefPositionRank refPositionRank);

	List<RefPositionRank> getByPositionId(@Param("positionId") Long positionId);
	
	int delByPositionIdAndRankId(@Param("positionId") Long positionId, @Param("rankId") Long rankId);
	
	int delByRankId(@Param("rankId") Long rankId);
	
	int delByPositionId(@Param("positionId") Long positionId);
	
	
}
