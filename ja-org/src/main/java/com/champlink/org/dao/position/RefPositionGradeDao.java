package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.org.position.RefPositionGrade;

@Mapper
public interface RefPositionGradeDao {
	int addPosition(RefPositionGrade refpositiongrade);

	int delById(@Param("userId") Long userId);

	List<RefPositionGrade> allList();
    
	List<RefPositionGrade> getByPositionId(@Param("positionId") Long positionId);
	List<RefPositionGrade> getByGradeId(@Param("gradeId") Long gradeId);
	
	int  delByPositionId(@Param("positionId") Long positionId);
}
