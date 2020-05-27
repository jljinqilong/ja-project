package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 询单评审历史记录
 * @Author created by barrett in 2018/8/13 16:02
 */
@Mapper
public interface InquiriesAppraisalHistoryDao {

    /**
     * 添加
     * @Author created by barrett in 18-8-6 下午5:42
     * @Param
     * @return
     */
    int insert(Inquiries record);

    /**
     * 修改
     * @Author created by barrett in 18-8-7 上午9:25
     * @Param
     * @return
     */
    int update(Inquiries record);

    /**
     * 批量删除询单记录（逻辑删除）
     * @Author created by barrett in 18-8-7 下午4:50
     * @Param
     * @return
     */
    int updateDelFlag(String[] idArr);

    /**
     * 查询
     * @Author created by barrett in 18-8-7 上午9:27
     * @Param
     * @return
     */
    Inquiries selectByRowId(Long rowId);

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:14
     * @Param
     * @return
     */
    Page<Inquiries> pageList(Paginater paginater);


}