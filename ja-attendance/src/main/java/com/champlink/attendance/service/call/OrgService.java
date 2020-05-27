package com.champlink.attendance.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "JA-ORG", fallback = OrgServiceFallback.class)
public interface OrgService {

    /**
     * 获取所有基地
     * 
     * @author natyu
     * @date 2018年7月28日 下午12:40:24
     * @return
     */
    @RequestMapping(value = "/org/baseList")
    public String baseList();

}
