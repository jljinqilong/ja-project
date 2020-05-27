package com.champlink.emolument.dao;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltSocialSecurity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EltSocialSecurityDao  {
	
	int add(EltSocialSecurity eltSocialSecurity);

	int delById(Long eltSocialSecurityId);

	int beachDel(@Param("list") Long[] ids);

	int empty();

	int update(EltSocialSecurity eltSocialSecurity);

	EltSocialSecurity getById(Long rowId);

	EltSocialSecurity getByName(String ruleName);

	Page<EltSocialSecurity> pageList(Paginater paginater);
	
}