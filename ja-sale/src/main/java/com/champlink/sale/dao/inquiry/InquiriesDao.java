package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiriesDao {

    /**
     * 添加询单记录
     * @Author created by barrett in 18-8-6 下午5:42
     * @Param
     * @return
     */
    int insert(Inquiries record);

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
    int update(Inquiries record);

    /**
     * 批量删除询单记录（逻辑删除）
     * @Author created by barrett in 18-8-7 下午4:50
     * @Param
     * @return
     */
    int updateDelFlag(@Param("idArr") String[] idArr,@Param("loginId")Long loginId);

    /**
     * 查询询单信息
     * @Author created by barrett in 18-8-7 上午9:27
     * @Param
     * @return
     */
    Inquiries getInquiries(Long rowId);

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:14
     * @Param
     * @return
     */
    Page<Inquiries> pageList(Paginater paginater);

    /**
     * 评审分页查询
     * @Author created by barrett in 2018/8/24 10:04
     */
    Page<Inquiries> appraisalPageList(Paginater paginater);

    /**
     * 改变询单状态
     * @Author created by barrett in 18-8-9 下午8:06
     */
    Integer updateStatus(@Param("rowId") Long rowId, @Param("status") int status,@Param("loginId")Long loginId);

    /**
     * 根据相应的条件查询
     * @param inquiries
     * @return
     */
    List<Inquiries> getInquiriesByInquiries(Inquiries inquiries);
    
}