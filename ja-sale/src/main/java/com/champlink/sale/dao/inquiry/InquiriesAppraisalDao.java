package com.champlink.sale.dao.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 询单评审
 * @Author created by barrett in 2018/8/13 16:02
 */
@Mapper
public interface InquiriesAppraisalDao {

    /**
     * 添加
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
    String getMaxInquiryNo(@Param("prefix") String prefix);
    /**
     * 修改
     * @Author created by barrett in 18-8-7 上午9:25
     * @Param
     * @return
     */
    int update(Inquiries record);

    /**
     * 批量删除记录（逻辑删除）
     * @Author created by barrett in 18-8-7 下午4:50
     * @Param
     * @return
     */
    int updateDelFlag(String[] idArr);

    /**
     * 查询详情
     * @Author created by barrett in 18-8-7 上午9:27
     * @Param
     * @return
     */
    Inquiries selectByRowId(Long rowId);

    /**
     * 改变状态
     * @Author created by barrett in 18-8-9 下午8:06
     */
    Integer updateStatus(@Param("rowId") Long rowId, @Param("status") int status,@Param("loginId")Long loginId);
}