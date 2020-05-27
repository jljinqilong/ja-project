package com.champlink.sale.service.payment;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.payment.PaymentPlan;
import com.champlink.common.form.payment.PaymentPlanForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.contract.SaleContractDao;
import com.champlink.sale.dao.payment.PaymentDao;
import com.champlink.sale.dao.payment.PaymentPlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/10 16:53
 **/
@Service
public class PaymentPlanService {

    @Autowired
    private PaymentPlanDao paymentPlanDao;

    @Autowired
    private SaleContractDao saleContractDao;

    @Autowired
    private PaymentDao paymentDao;


    /**
     * @Author jsl
     * @Date 增加付款计划 2018/8/13
     * @Description
     **/
    @Transactional
    public boolean add(PaymentPlan paymentPlan) {
        //判断计划总金额 不能超过合同总金额
        Long saleContractId = paymentPlan.getSaleContractId();
        //回款计划已回款金额
        paymentPlan.setPlan_paymentAmount(new BigDecimal(0.0));
        paymentPlan.setPlan_unPaymentAmount(paymentPlan.getPayAmount());

        //根据合同id查询已存在的计划金额总和
        BigDecimal nowTotalAmount = paymentPlanDao.getPlanTotalAmountById(saleContractId);
        BigDecimal contractTotalAmount = saleContractDao.selectTotleAmountById(saleContractId);
        if(contractTotalAmount.compareTo(nowTotalAmount.add(paymentPlan.getPayAmount()))>=0){
            paymentPlanDao.add(paymentPlan);
            //更新合同中的已计划和未计划金额
            saleContractDao.updatePlanedAmount(saleContractId,paymentPlan.getPayAmount());
        }else{
            AppException.create(310006);
        }
        return true;
    }

    /**
     * @Author jsl
     * @Date 查询 2018/8/13
     * @Description    
     **/
    public PageListVO<PaymentPlan> searchList(PaymentPlanForm paymentPlanForm) {
        Paginater paginater = Paginater.newInstance(paymentPlanForm);

        PageListVO<PaymentPlan> listVO = PageListVO.newInstance(paymentPlanDao.searchList(paginater));
        return listVO;
    }

    /**
     * @Author jsl
     * @Date 删除 2018/8/13
     * @Description
     **/
    @Transactional
    public boolean del(Long payPlanId) {

        //查询是否有回款记录，存在不让删除
        int num = paymentPlanDao.selectPaymentDetailById(payPlanId);
        if(num>0){
            AppException.create(310007);
        }else{
            //先查询该回款计划单
            PaymentPlan  paymentPlan = new PaymentPlan();
            paymentPlan = getById(payPlanId);
            BigDecimal payAmount = paymentPlan.getPayAmount();
            Long saleContractId = paymentPlan.getSaleContractId();
            //更新合同中的已计划和为计划金额
            saleContractDao.updatePlanedAmount(saleContractId,new BigDecimal(0.0).subtract(payAmount));
            //删除计划
            if(paymentPlanDao.del(payPlanId)){
                return true;
            }
        }
         return false;
    }

    /**
     * @Author jsl
     * @Date 更新 2018/8/13
     * @Description
     **/
    @Transactional
    public boolean update(PaymentPlan paymentPlan) {
        //这里更新确保不影响到回款记录 必须保证有回款记录的状态下不能更新
        //这里默认没有回款记录的情况
        Long saleContractId = paymentPlan.getSaleContractId();

        //根据回款计划单id，查询此计划单下已经回款的金额
        BigDecimal nowPaymentAmount = paymentDao.getPaymentAmountByPlanId(paymentPlan.getRowId());


        //根据合同id查询已存在的计划金额总和（不包含本计划单）
        BigDecimal nowTotalAmountNoThis = paymentPlanDao.getPlanTotalAmountNoThisById(saleContractId,paymentPlan.getRowId());

        BigDecimal contractTotalAmount = saleContractDao.selectTotleAmountById(saleContractId);
        if(nowPaymentAmount.compareTo(paymentPlan.getPayAmount())>0){
            AppException.create(310008);    //更改金额不能小于该回款单已回款的总金额
        }else if(contractTotalAmount.compareTo(nowTotalAmountNoThis.add(paymentPlan.getPayAmount()))<0){
            AppException.create(310009);    //更改后的计划金额不能大于合同总金额
        }else {
            paymentPlanDao.update(paymentPlan);
            //更新合同中的已计划和未计划金额
            //1.获取该合同下所有的计划总和
            BigDecimal allPlanedAmount = paymentPlanDao.getPlanTotalAmountById(saleContractId);
            //2.更新合同中的已计划和为计划金额
            saleContractDao.updatePlanedAmountById(saleContractId,allPlanedAmount);

        }
        return true;
    }

    /**
     * @Author jsl
     * @Date 根据id查询 2018/8/13
     * @Description    
     **/
    public PaymentPlan getById(Long rowId) {
        PaymentPlan paymentPlan = new PaymentPlan();
        paymentPlan = paymentPlanDao.get(rowId);
        return paymentPlan;
    }

    /**
     * @Author jsl
     * @Date 自动获取其次 和 可计划金额 2018/8/13   修改用
     * @Description    
     **/
    public PaymentPlan getUpdatePeriodAndApplicableAmount(Long saleContractId,Long payPlanId) {
        //期次
        int num = paymentPlanDao.getPeriod(saleContractId);
        String period = String.valueOf(num+1);

        //可计划金额
        SaleContract saleContract = saleContractDao.getSaleContractById(saleContractId);
        //根据合同id查询已计划总金额
        BigDecimal allPlanAmount = paymentPlanDao.getPlanTotalAmountById(saleContractId);
        //获取当次计划单得金额
        PaymentPlan currPaymentPlan = paymentPlanDao.get(payPlanId);
        BigDecimal currPaymentPlanAmount = currPaymentPlan.getPayAmount();


        PaymentPlan paymentPlan = new PaymentPlan();
        paymentPlan.setPeriod(period);
        paymentPlan.setApplicableAmount(saleContract.getTotalAmount().subtract(allPlanAmount).add(currPaymentPlanAmount));

        return paymentPlan;
    }

    //新增时获取期次和最大可计划金额
    @Transactional
    public PaymentPlan getAddPeriodAndApplicableAmount(Long saleContractId) {
        //期次
        int num = paymentPlanDao.getPeriod(saleContractId);
        String period = String.valueOf(num+1);

        //可计划金额
        SaleContract saleContract = saleContractDao.getSaleContractById(saleContractId);
        //根据合同id查询已计划总金额
        BigDecimal allPlanAmount = paymentPlanDao.getPlanTotalAmountById(saleContractId);

        PaymentPlan paymentPlan = new PaymentPlan();
        paymentPlan.setPeriod(period);

        if(saleContract.getTotalAmount() != null)
            paymentPlan.setApplicableAmount(saleContract.getTotalAmount().subtract(allPlanAmount));

        return paymentPlan;
    }
}
