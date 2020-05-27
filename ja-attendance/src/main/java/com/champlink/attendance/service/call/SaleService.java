package com.champlink.attendance.service.call;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "JA-SALE", fallback = SaleServiceFallBack.class)
public interface SaleService {

    @PostMapping(value = "/countryCascader/searchDetailListByRowId", produces = "application/json;charset=UTF-8")
    public String searchDetailListByRowId(@RequestBody List<Long> ids);

    @PostMapping(value = "/countryCascader/selectRegionListByPId", produces = "application/json;charset=UTF-8")
    public String selectRegionListByPId(@RequestBody Long pid);

}
