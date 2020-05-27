package com.champlink.org.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champlink.common.domain.org.OrgAllBackup;
import com.champlink.org.dao.OrgAllBackupDao;

@Service
public class OrgAllBackupService {

	@Autowired
    private OrgAllBackupDao orgAllBackupDao;
	 
	public int addList(List<OrgAllBackup> list) {
		return orgAllBackupDao.add(list);
	}
	
	public int del(Date backUpTime) {
		return orgAllBackupDao.deleteByBackUpTime(backUpTime);
	}
	
	public List<OrgAllBackup> findByBackUpTimeAndDeptIdList(Date backUpTime, List<Long> deptIdList) {
		return orgAllBackupDao.findByBackUpTimeAndDeptIdList(backUpTime, deptIdList);
	}
	
	public List<OrgAllBackup> findByBackUpTime(Date backUpTime) {
		return orgAllBackupDao.findByBackUpTime(backUpTime);
	}
	
    public OrgAllBackup findOneByRowId(Long rowId, Date date) {
    	return orgAllBackupDao.findOneByRowId(date,rowId);
    }
    
    public OrgAllBackup findOneByParentId(Long parentId, Date date) {
    	OrgAllBackup findOneByParentId = orgAllBackupDao.findOneByParentId(date, parentId);
    	return findOneByParentId;
    }
}
