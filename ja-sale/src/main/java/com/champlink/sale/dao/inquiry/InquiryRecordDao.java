package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InquiryRecordDao {

    /**
     * 添加询价记录
     * @Author created by barrett in 18-8-6 下午5:42
     * @Param
     * @return
     */
    int insert(InquiryRecord record);

    /**
     * 获取最大编码值
     * @Author created by barrett in 18-8-6 下午7:39
     * @Param
     * @return
     */
    String getMaxInquiryNo(@Param("prefix")String prefix);
    /**
     * 修改
     * @Author created by barrett in 18-8-7 上午9:25
     * @Param
     * @return
     */
    int update(InquiryRecord record);

    /**
     * 批量删除询价记录（逻辑删除）
     * @Author created by barrett in 18-8-7 下午4:50
     * @Param
     * @return
     */
    int updateDelFlag(@Param("idArr") String[] idArr,@Param("loginId")Long loginId);

    /**
     * 查询询价信息
     * @Author created by barrett in 18-8-7 上午9:27
     * @Param
     * @return
     */
    InquiryRecord selectByRowId(Long rowId);

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:14
     * @Param
     * @return
     */
    Page<InquiryRecord> pageList(Paginater paginater);

    /**
     * 改变询价状态
     * @Author created by barrett in 18-8-9 下午8:06
     */
    Integer updateInquiryStatus(@Param("rowId") Long rowId, @Param("status") int status, @Param("loginId") Long loginId);


}