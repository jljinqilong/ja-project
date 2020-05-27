package com.champlink.emolument.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.vo.PageListVO;

@Mapper
public interface EltRefStaffEmolumentDao {

    int deleteByStaffId(Long staffId);

    int deleteRuleId(Long ruleId);

    PageListVO<EltRefStaffEmolument> select(Paginater paginater);

    int insert(List<EltRefStaffEmolument> list);

}