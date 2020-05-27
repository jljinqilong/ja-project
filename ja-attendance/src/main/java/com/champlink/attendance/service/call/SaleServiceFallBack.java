package com.champlink.attendance.service.call;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SaleServiceFallBack implements SaleService {

    @Override
    public String searchDetailListByRowId(List<Long> ids) {
        return null;
    }

    @Override
    public String selectRegionListByPId(Long pid) {
        return null;
    }

}
