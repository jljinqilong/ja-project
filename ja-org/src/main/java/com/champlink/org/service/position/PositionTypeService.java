package com.champlink.org.service.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.PositionType;
import com.champlink.common.form.org.position.PositionTypeForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.org.dao.position.PositionDao;
import com.champlink.org.dao.position.PositionTypeDao;

@Service
public class PositionTypeService {

	@Autowired
	private PositionTypeDao positiontypeDao;
	
	@Autowired
	private PositionDao positionDao;

	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 */
	public boolean add(PositionType positiontype) {
		
		List<PositionType> allPositionType = positiontypeDao.AllPositionType();
		for (PositionType positionType2 : allPositionType) {
			if(positionType2.getTypeName().equals(positiontype.getTypeName())) {
				AppException.create(500005);
			}
		}
		if (positiontypeDao.add(positiontype) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除
	 * 
	 * @param
	 * @return
	 */
	public boolean del(Long rowId) {
		List<Position> allPosition = positionDao.getAllPosition();
		for (Position position : allPosition) {
			if(position.getPositionType().intValue() == rowId.longValue()) {
				AppException.create(500005);
			}
		}
		
		if (positiontypeDao.delById(rowId) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PositionType positiontype) {
		
		PositionType dbPosition = positiontypeDao.getById(positiontype.getRowId());
		
		if(!dbPosition.getTypeName().equals(positiontype.getTypeName().trim())) {
			List<PositionType> allPositionType = positiontypeDao.AllPositionType();
			for (PositionType positionType2 : allPositionType) {
				if(positionType2.getTypeName().equals(positiontype.getTypeName())) {
					AppException.create(500005);
				}
			}
		}
		
		if (positiontypeDao.update(positiontype) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取列表
	 * 
	 * @param form
	 * @return
	 */
	public PageListVO pageList(PositionTypeForm form) {
		Paginater paginater = Paginater.newInstance(form);
		PageListVO pageListVO = PageListVO.newInstance(positiontypeDao.pageList(paginater));
		return pageListVO;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public PositionType findOne(Long id) {
		PositionType positiontype = positiontypeDao.getById(id);
		return positiontype;
	}

	public List<PositionType> getByPositionType() {
		return positiontypeDao.AllPositionType();
	}

}
