package com.champlink.sale.service.payment;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.champlink.common.form.payment.PaymentPlanDetailForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.contract.SaleContractDao;
import com.champlink.sale.dao.payment.PaymentDao;
import com.champlink.sale.dao.payment.PaymentPlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/9 10:58
 **/
@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private SaleContractDao saleContractDao;
    @Autowired
    private PaymentPlanDao paymentPlanDao;

    /**
     * @Author jsl
     * @Date 查询list 2018/8/9
     * @Description
     **/
    public PageListVO<PaymentPlanDetail> searchList(PaymentPlanDetailForm paymentPlanDetailForm) {
        Paginater paginater = Paginater.newInstance(paymentPlanDetailForm);

        PageListVO<PaymentPlanDetail> listVO = PageListVO.newInstance(paymentDao.searchList(paginater));
        return listVO;
    }


    /**
     * @Author jsl
     * @Date 增加 2018/8/9
     * @Description
     **/
    @Transactional
    public boolean add(PaymentPlanDetail paymentPlanDetail) {
        //合同id
        Long saleContractId = paymentPlanDetail.getSaleContractId();
        //回款计划id
        Long payPlanId = paymentPlanDetail.getPayPlanId();
        //回款金额
        BigDecimal amount = paymentPlanDetail.getAmount();

        //该回款计划下已经回款总金额
        BigDecimal plan_totalAmount = paymentDao.getPlanTotleAmountById(payPlanId);
        //查询该回款计划的计划金额
        BigDecimal planAmount = paymentPlanDao.getPlanAmountByPlanId(payPlanId);

        //本次回款金额 + 已经回款金额 《= 计划回款金额

        if(planAmount.compareTo(paymentPlanDetail.getAmount().add(plan_totalAmount))>=0){
            //add 回款记录
            paymentDao.add(paymentPlanDetail);

            //更新回款计划 已回款和未回款记录
            paymentPlanDao.updatePlanPaymentAmount(payPlanId,paymentPlanDetail.getAmount());

            //修改合同的已回款金额和未回款金额
            saleContractDao.updatePayMentAmount(saleContractId,amount);
        }else{
            AppException.create(310010);    //回款记录总金额不能超过 计划回款金额
        }

        return true;
    }

    /**
     * @Author jsl
     * @Date 14:48 2018/8/9
     * @Description
     **/
    @Transactional
    public boolean del(Long id) {
        //删除回款明细  更新合同已回款和未回款金额
        //1.根据id查询回款明细数据
        PaymentPlanDetail paymentPlanDetail = get(id);
        BigDecimal amount = paymentPlanDetail.getAmount();
        Long saleContarctId= paymentPlanDetail.getSaleContractId();
        //2.删除回款明细记录
        paymentDao.del(id);

        //更新回款计划的回款金额和未回款金额
        paymentPlanDao.updatePlanPaymentAmount(paymentPlanDetail.getPayPlanId(),new BigDecimal(0.0).subtract(amount));

        //3.更新合同的已回款金额和未回款金额
        saleContractDao.updatePayMentAmount(saleContarctId,new BigDecimal(0).subtract(amount));

        return true;
    }

    /**
     * @Author jsl
     * @Date 根据id查询单条的回款明细 2018/8/9
     * @Description
     **/
    public PaymentPlanDetail get(Long rowId) {
        PaymentPlanDetail paymentPlanDetail = paymentDao.get(rowId);
        return paymentPlanDetail;
    }

    /**
     * @Author jsl
     * @Date 修改 2018/8/9
     * @Description
     **/
    public boolean update(PaymentPlanDetail paymentPlanDetail) {

        //合同id
        Long saleContractId = paymentPlanDetail.getSaleContractId();
        //回款计划id
        Long payPlanId = paymentPlanDetail.getPayPlanId();

        //判断本次更新的金额 不能超过 计划总金额
        //计划总金额
        BigDecimal payAmount = paymentPlanDao.getPlanAmountByPlanId(payPlanId);
        //计划下 不含本次的 回款总金额
        BigDecimal paymentAmountNoThis = paymentDao.getPaymentAmountNotThis(payPlanId,paymentPlanDetail.getRowId());
        if(payAmount.compareTo(paymentAmountNoThis.add(paymentPlanDetail.getAmount()))>=0) {

            //更新回款明细
            paymentDao.update(paymentPlanDetail);


            //更新回款计划 已回款和未回款金额
            //该回款计划下 所有的已回款金额
            BigDecimal plan_totalAmount = paymentDao.getPaymentAmountByPlanId(payPlanId);
            //更新回款计划  已回款金额和未回款金额
            paymentPlanDao.updatePlanPaymentAmountById(payPlanId, plan_totalAmount);

            //更新合同已回款和未回款记录
            //1.取所有的回款明细金额总和
            BigDecimal total_amount = saleContractDao.getTotalPaymentAmount(saleContractId);
            saleContractDao.updatePayMentAmountById(saleContractId, total_amount);
        }else{
            AppException.create(310010);      //已回款金额不能超过计划回款金额
        }

        return true;
    }

    /**
     * @Author jsl
     * @Date 查询回款记录 2018/8/14
     * @Description
     **/
    public List<PaymentPlanDetail> getPaymentDetailList(Long payPlanId) {
        List<PaymentPlanDetail> paymentPlanDetailList = new ArrayList<PaymentPlanDetail>();

        paymentPlanDetailList = paymentDao.getPaymentDetailById(payPlanId);

        return paymentPlanDetailList;
    }
}
