package com.champlink.attendance.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.CreditCardLog;
import com.github.pagehelper.Page;

@Mapper
public interface CreditCardLogMapper {
    int deleteByPrimaryKey(Long rowId);

    int insert(CreditCardLog record);

    CreditCardLog selectByPrimaryKey(Long rowId);

    List<CreditCardLog> selectAll();

    int updateByPrimaryKey(CreditCardLog record);

	Page pageList(Paginater paginater);
	
	int insertList(List<CreditCardLog> creditCardLogList);
}