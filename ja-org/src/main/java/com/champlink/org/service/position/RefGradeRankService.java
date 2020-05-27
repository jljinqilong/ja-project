package com.champlink.org.service.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.domain.org.position.RefGradeRank;
import com.champlink.org.dao.position.RefGradeRankDao;

@Service
public class RefGradeRankService {

	@Autowired
	private RefGradeRankDao refGradeRankDao;

	public List<RefGradeRank> getByRankType() {
		return refGradeRankDao.getByRankType();
	}

}
