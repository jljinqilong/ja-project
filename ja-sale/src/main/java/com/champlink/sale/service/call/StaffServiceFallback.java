package com.champlink.sale.service.call;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaffServiceFallback implements StaffService {

    @Override
    public String getUserInfo(Long rowId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBaseInfo(String userName) {
        return null;
    }

    @Override
    public String getSalePersonList() {
        return null;
    }

}
