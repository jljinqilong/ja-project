package com.champlink.sale.dao.payment;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PaymentDao {
    Page<PaymentPlanDetail> searchList(Paginater paginater);

    /**
     * @Author jsl
     * @Date 增加 2018/8/9
     * @Description
     **/
    boolean add(PaymentPlanDetail paymentPlanDetail);

    /**
     * @Author jsl
     * @Date 根据rowId获取单条回款明细数据 2018/8/9
     * @Description    
     **/
    PaymentPlanDetail get(Long rowId);

    /**
     * @Author jsl
     * @Date 删除回款明细 2018/8/9 
     * @Description    
     **/
    void del(Long id);

    /**
     * @Author jsl
     * @Date 更新 2018/8/9 
     * @Description    
     **/
    void update(@Param("record") PaymentPlanDetail record);

    /**
     * @Author jsl
     * @Date 根据合同id查询回款记录 2018/8/9
     * @Description
     **/
    PaymentPlanDetail searchById(Long rowId);

    /**
     * @Author jsl
     * @Date 根据计划单查询已回款的金额 2018/8/13
     * @Description
     **/
    BigDecimal getPaymentAmountByPlanId(Long rowId);

    /**
     * @Author jsl
     * @Date 根据回款计划id查询已回款总金额 2018/8/14
     * @Description
     **/
    BigDecimal getPlanTotleAmountById(Long payPlanId);

    /**
     * @Author jsl
     * @Date 不包含本条记录以外的其他已回款记录总金额 2018/8/14
     * @Description
     **/
    BigDecimal getPaymentAmountNotThis(@Param("payPlanId") Long payPlanId, @Param("rowId") Long rowId);

    /**
     * @Author jsl
     * @Date 根据回款计划id查询回款记录 2018/8/14
     * @Description
     **/
    List<PaymentPlanDetail> getPaymentDetailById(Long payPlanId);
}
