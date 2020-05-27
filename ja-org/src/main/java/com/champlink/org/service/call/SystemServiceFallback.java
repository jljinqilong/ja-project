package com.champlink.org.service.call;

import java.util.List;

import org.springframework.stereotype.Component;
import com.champlink.common.domain.system.ModuleLog;

@Component
public class SystemServiceFallback implements SystemService {

    @Override
    public String generate(String code, String num) {
        
        return null;
    }

	@Override
	public String addOrgToUpdateData(List<Long> orgIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
