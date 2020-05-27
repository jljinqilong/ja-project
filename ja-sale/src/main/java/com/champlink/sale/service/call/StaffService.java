package com.champlink.sale.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "JA-STAFF", fallback = StaffServiceFallback.class)
public interface StaffService {

    @RequestMapping("/baseInfo/get/{id}")
    public String getUserInfo(@PathVariable("id")Long rowId);

    @RequestMapping("/baseInfo/getBaseInfo/{userName}")
    public String getBaseInfo(@PathVariable("userName") String userName);

    @RequestMapping("/baseInfo/getSalePersonList")
    public String getSalePersonList();

}
