package com.champlink.sale.generator.mapper;

import com.champlink.sale.generator.pojo.InquiriesProduct;
import com.champlink.sale.generator.pojo.InquiriesProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InquiriesProductMapper {
    int countByExample(InquiriesProductExample example);

    int deleteByExample(InquiriesProductExample example);

    int deleteByPrimaryKey(Long rowId);

    int insert(InquiriesProduct record);

    int insertSelective(InquiriesProduct record);

    List<InquiriesProduct> selectByExample(InquiriesProductExample example);

    InquiriesProduct selectByPrimaryKey(Long rowId);

    int updateByExampleSelective(@Param("record") InquiriesProduct record, @Param("example") InquiriesProductExample example);

    int updateByExample(@Param("record") InquiriesProduct record, @Param("example") InquiriesProductExample example);

    int updateByPrimaryKeySelective(InquiriesProduct record);

    int updateByPrimaryKey(InquiriesProduct record);
}