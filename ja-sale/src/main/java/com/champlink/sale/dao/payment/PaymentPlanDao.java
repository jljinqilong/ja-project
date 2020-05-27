package com.champlink.sale.dao.payment;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.payment.PaymentPlan;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PaymentPlanDao {
    /**
     * @Author jsl
     * @Date 新增 2018/8/13
     * @Description
     **/
    boolean add(PaymentPlan paymentPlan);

    /**
     * @Author jsl
     * @Date 查询 2018/8/13
     * @Description
     **/
    Page<PaymentPlan> searchList(Paginater paginater);

    /**
     * @Author jsl
     * @Date 删除 2018/8/13
     * @Description
     **/
    boolean del(Long id);

    /**
     * @Author jsl
     * @Date 根据id查询 2018/8/13
     * @Description
     **/
    PaymentPlan get(Long rowId);

    /**
     * @Author jsl
     * @Date 更新 2018/8/13
     * @Description    
     **/
    boolean update(PaymentPlan paymentPlan);


    List<PaymentPlan> getContractInfoBy(Long saleContractId);

    /**
     * @Author jsl
     * @Date 根据合同id查询该合同下已存在的计划金额总和 2018/8/13
     * @Description
     **/
    BigDecimal getPlanTotalAmountById(Long saleContractId);

    /**
     * @Author jsl
     * @Date 更具回款计划id查询是否已经回款 2018/8/13
     * @Description    
     **/
    int selectPaymentDetailById(Long payPlanId);

    /**
     * @Author jsl
     * @Date 查询合同所有的计划金额总和，不包含此条 2018/8/13
     * @Description
     **/
    BigDecimal getPlanTotalAmountNoThisById(@Param("saleContractId") Long saleContractId, @Param("rowId") Long rowId);

    /**
     * @Author jsl
     * @Date 获取其次 2018/8/13
     * @Description
     **/
    int getPeriod(Long saleContractId);

    /**
     * @Author jsl
     * @Date 根据回款计划id查询计划回款金额 2018/8/14
     * @Description
     **/
    BigDecimal getPlanAmountByPlanId(Long payPlanId);

    /**
     * @Author jsl
     * @Date 根据id和本次回款金额，更新计划中的已回款和未回款金额 2018/8/14 
     * @Description    
     **/
    void updatePlanPaymentAmount(@Param("payPlanId") Long payPlanId, @Param("amount") BigDecimal amount);

    /**
     * @Author jsl
     * @Date 根据id更新计划中的已回款和未回款金额 2018/8/14
     * @Description
     **/
    void updatePlanPaymentAmountById(@Param("payPlanId") Long payPlanId, @Param("plan_totalAmount") BigDecimal plan_totalAmount);
}
