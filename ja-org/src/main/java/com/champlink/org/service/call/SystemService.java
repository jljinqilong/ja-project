package com.champlink.org.service.call;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.champlink.common.domain.system.ModuleLog;

@FeignClient(value = "JA-SYSTEM", fallback = SystemServiceFallback.class)
public interface SystemService {

    /**
     * 生成编码
     * 
     * @param code
     * @return
     */
    @RequestMapping(value = "/code/generate/{code}/{num}")
    public String generate(@PathVariable("code") String code, @PathVariable("num") String num);
    
    @RequestMapping(value = "/role/addOrgToUpdateData")
    public String addOrgToUpdateData(@RequestParam("orgIds") List<Long> orgIds);
    
}
