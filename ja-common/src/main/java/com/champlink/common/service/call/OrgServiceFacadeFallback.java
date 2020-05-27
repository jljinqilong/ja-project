package com.champlink.common.service.call;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgServiceFacadeFallback implements OrgServiceFacade{

	@Override
	public String getOrgNameByOrgIds(List<Long> ids) {
		return null;
	}

	@Override
	public String baseList() {
		return null;
	}
}
