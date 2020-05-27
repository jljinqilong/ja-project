package com.champlink.staff.service.call;

import java.util.List;
import org.springframework.stereotype.Component;
import com.champlink.common.domain.system.ModuleLog;

@Component
public class SystemServiceFallback implements SystemService {

    @Override
    public String getAllCode() {
        return null;
    }

    @Override
    public String getByTpye(String typeCode) {
        return null;
    }

    @Override
    public String add(ModuleLog moduleLog) {
        return null;
    }

    @Override
    public String generate(String code, String num) {
        return null;
    }

    @Override
    public String batchAdd(List<String> list) {
        return null;
    }

    @Override
    public String delByUserName(String userName) {
        return null;
    }

    @Override
    public String changeByUserName(String userName, int status) {
        return null;
    }

    @Override
    public String changeByUserNameList(List<String> userNameList, int status) {
        return null;
    }

    @Override
    public String updateUserName(String oldUserName, String newUserName) {
        return null;
    }

	@Override
	public String allList() {
		// TODO Auto-generated method stub
		return null;
	}

}
