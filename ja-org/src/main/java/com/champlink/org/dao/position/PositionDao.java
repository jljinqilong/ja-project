package com.champlink.org.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.form.org.position.SearchPositionForm;
import com.champlink.common.vo.PositionVO;
import com.github.pagehelper.Page;

@Mapper
public interface PositionDao {

	int add(Position position);

	int delById(Long userId);

	int update(PositionVO position);

	Position getById(Long rowId);


	Page<PositionVO> pageList(Paginater paginater);

	List<Position> queryPositionList(Long id);

	List<Position> getByPositionType();

	List<PositionVO> queryPositionForParams(SearchPositionForm form);

	Integer insertPositionList(@Param("list") List<PositionVO> list);

	List<PositionVO> exportList(SearchPositionForm form);
	
	List<Position> getAllPosition();
	
}