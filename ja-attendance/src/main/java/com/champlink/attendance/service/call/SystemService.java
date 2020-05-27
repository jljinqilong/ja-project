package com.champlink.attendance.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "JA-SYSTEM", fallback = SystemServiceFallback.class)
public interface SystemService {

    /**
     * 查询字典列表
     * 
     * @param typeCode
     * @return
     */
    @RequestMapping(value = "/code/getByTpye")
    public String getByTpye(@RequestParam("typeCode") String typeCode);

}
