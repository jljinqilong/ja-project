package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiriesProductDao {

    int insert(InquiriesProduct record);

    int insertProductBatch(List<InquiriesProduct> list);

    int update(InquiriesProduct record);

    Page<InquiriesProduct> pageList(Paginater paginater);

    int delete(Long rowId);

    int delByinquiriesId(Long rowId);

    int updateDel(Long rowId);

    InquiriesProduct getInquiriesProduct(Long rowId);

    List<InquiriesProduct> getInquiriesProductList(@Param("inquiriesId")Long rowId);

}