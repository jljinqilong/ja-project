package com.champlink.emolument.service.call;

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

}
