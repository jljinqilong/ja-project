package com.champlink.org.service.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.domain.org.position.RefPositionGrade;
import com.champlink.org.dao.position.RefPositionGradeDao;

@Service
public class RefPositionGradeService {

	@Autowired
	private RefPositionGradeDao refPositionGradeDao;

	public List<RefPositionGrade> getByGradeType() {
		return refPositionGradeDao.allList();
	}

}
