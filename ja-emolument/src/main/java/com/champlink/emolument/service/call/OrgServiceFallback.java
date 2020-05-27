package com.champlink.emolument.service.call;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgServiceFallback implements OrgService{

	@Override
	public String getOrgNameByOrgIds(List<Long> ids) {
		return null;
	}

	@Override
	public String baseList() {
		return null;
	}
}
