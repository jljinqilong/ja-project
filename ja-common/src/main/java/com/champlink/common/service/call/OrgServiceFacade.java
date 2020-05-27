package com.champlink.common.service.call;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "JA-ORG", fallback = OrgServiceFacadeFallback.class)
public interface OrgServiceFacade {

    @RequestMapping("/org/getOrgNameByOrgIds/{ids}")
    public String getOrgNameByOrgIds(@PathVariable("ids") List<Long> ids);

    /**
     * 获取所有基地
     * @return
     */
    @RequestMapping(value = "/org/baseList")
    public String baseList();

}
