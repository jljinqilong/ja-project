package com.champlink.attendance.service.call;

import org.springframework.stereotype.Component;

import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;

@Component
public class StaffServiceFallback implements StaffService {

    @Override
    public boolean checkStaffNo(String staffNo) {
    	
        return false;
    }

	@Override
	public String queryBaseInfoForParams(SearchBaseInfoForm form) {
		return null;
	}

	@Override
	public String getStfBaseInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseInfo(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseInfoByStaffNo(String staffNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
