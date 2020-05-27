package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.InquiryRecordDetail;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 多条询价明细对应一条询价记录
 * @Author created by barrett in 18-8-8 上午9:52
 */
@Mapper
public interface InquiryRecordDetailDao {

    /**
     * 添加
     * @Author created by barrett in 18-8-8 上午9:59
     */
    int insert(InquiryRecordDetail record);

    /**
     * 修改
     * @Author created by barrett in 18-8-8 上午9:59
     * @Param
     * @return
     */
    int update(InquiryRecordDetail record);

    /**
     * 批量删除询价记录（逻辑删除）
     * @Author created by barrett in 18-8-8 上午9:59
     * @Param
     * @return
     */
    int updateDelFlag(@Param("idArr") String[] idArr,@Param("loginId")Long loginId);

    /**
     * 查询询价信息
     * @Author created by barrett in 18-8-8 上午9:59
     * @Param
     * @return
     */
    InquiryRecordDetail selectByRowId(Long rowId);
    /**
     * 根据询价id获取询单list
     * @Author created by barrett in 18-8-8 上午10:37
     */
    List<InquiryRecordDetail> selectInquiryRecordDetailByRowId(Long rowId);

    /**
     * 分页查询
     * @Author created by barrett in 18-8-8 上午9:59
     * @Param
     * @return
     */
    Page<InquiryRecordDetail> pageList(Paginater paginater);



}