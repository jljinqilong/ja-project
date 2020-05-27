package com.champlink.attendance.service.call;

import org.springframework.stereotype.Component;

@Component
public class OrgServiceFallback implements OrgService {

    @Override
    public String baseList() {
        return null;
    }

}
