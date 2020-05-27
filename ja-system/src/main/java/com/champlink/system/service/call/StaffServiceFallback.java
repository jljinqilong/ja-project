package com.champlink.system.service.call;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StaffServiceFallback implements StaffService {

    @Override
    public boolean checkStaffNo(String staffNo) {
        return false;
    }

    @Override
    public String getBaseInfoByStaffNo(String staffNo) {
        return null;
    }

    @Override
    public String getStaffIdByOrgIds(List<Long> list) {
        return null;
    }

	@Override
	public String getStaffNoBystaffIds(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
