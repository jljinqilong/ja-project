package com.champlink.attendance.service.call;

import org.springframework.stereotype.Component;

@Component
public class SystemServiceFallback implements SystemService {


    @Override
    public String getByTpye(String typeCode) {
        return null;
    }

}
