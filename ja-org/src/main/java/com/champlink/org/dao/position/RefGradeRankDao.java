package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.org.position.RefGradeRank;

@Mapper
public interface RefGradeRankDao {

	int addRefGradeRank(RefGradeRank refgraderank);

	int delById(@Param("userId") Long userId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */

	List<RefGradeRank> getByRankType();

	List<RefGradeRank> getByGradeId(@Param("gradeId") Long gradeId);
	
	int delByGradeId(@Param("gradeId") Long gradeId);
	
	int delByRankId(@Param("rankId") Long rankId);
	
	List<RefGradeRank> allList();
}
