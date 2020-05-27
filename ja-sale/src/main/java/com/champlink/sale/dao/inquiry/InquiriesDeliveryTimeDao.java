package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.InquiriesDeliveryTime;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiriesDeliveryTimeDao {

    // 询单交期 分页
    Page<InquiriesDeliveryTime> pageList(Paginater paginater);

    int insert(InquiriesDeliveryTime record);

    int update(InquiriesDeliveryTime record);

    int insertDeliveryTimesBatch(List<InquiriesDeliveryTime> list);

    int delByinquiriesId(Long rowId);

    List<InquiriesDeliveryTime> getInquiriesDeliveryTimeList(@Param("inquiriesId") Long rowId);

}