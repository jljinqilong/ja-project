package com.champlink.common.service.call;

import org.springframework.stereotype.Component;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;

@Component
public class StaffServiceFacadeFallback implements StaffServiceFacade {
    @Override
    public String getUserInfo(Long rowId) {
        return null;
    }

    @Override
    public String getBaseInfo(String userName) {
        return null;
    }

    @Override
    public String getUserNameById(Long rowId) {
        return null;
    }

    @Override
    public String queryBaseInfoForParams(SearchBaseInfoForm form) {
        return null;
    }

}
