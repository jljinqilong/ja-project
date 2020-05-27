package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 询单历史记录
 * @Author created by barrett in 2018/8/13 16:02
 */
@Mapper
public interface InquiriesHistoryDao {

    /**
     * 添加询单历史记录
     * @Author created by barrett in 18-8-6 下午5:42
     * @Param
     * @return
     */
    int insert(Inquiries record);

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:14
     * @Param
     * @return
     */
    Page<Inquiries> pageList(Paginater paginater);


}