package com.champlink.sale.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * 调用org库
 * @Author created by barrett in 18-8-7 上午10:17
 * @Param
 * @return
 */
@FeignClient(value = "JA-ORG", fallback = OrgServiceFallback.class)
public interface OrgService {

    @RequestMapping("/org/get/{rowId}")
    public String getOrgById(@PathVariable("rowId") Long rowId);


}
