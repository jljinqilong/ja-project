package com.champlink.org.service.call;

import java.util.List;

import org.springframework.stereotype.Component;

import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;

@Component
public class StaffServiceFallback implements StaffService {

    @Override
    public String queryBaseInfo(Long deptId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String orgMerge(Long sourceId, Long targetId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String delDept_ids(List<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkStaffNo(String staffNo) {
        // TODO Auto-generated method stub
        return false;
    }

	@Override
	public String getBaseInfoByStaffNo(String staffNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String queryCountBaseInfoByPositionId(Long positionId) {
		return null;
	}

	@Override
	public List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseInfo(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
