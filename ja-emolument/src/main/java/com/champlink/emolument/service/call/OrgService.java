package com.champlink.emolument.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//
@FeignClient(value = "JA-ORG", fallback = OrgServiceFallback.class)
public interface OrgService {

	@RequestMapping("/org/getOrgNameByOrgIds/{ids}")
	public String getOrgNameByOrgIds(@PathVariable("ids") List<Long> ids);

	/**
	 * 获取所有基地
	 * @return
	 */
	@RequestMapping(value = "/org/baseList")
	public String baseList();
	
}
