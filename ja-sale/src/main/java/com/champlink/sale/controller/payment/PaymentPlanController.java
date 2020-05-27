package com.champlink.sale.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.payment.PaymentPlan;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.payment.PaymentPlanForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.payment.PaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desciption 回款计划
 * @Author JSL
 * @Date 2018/8/10 16:48
 **/
@RequestMapping("/paymentPlan")
@RestController
public class PaymentPlanController extends BaseController {


    @Autowired
    private StaffService staffService;
    @Autowired
    private PaymentPlanService paymentPlanService;
    @Autowired
    private SaleCommonService saleCommonService;


    /**
     * @Author jsl
     * @Date 查询回款计划 2018/8/9
     * @Description
     **/
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String searchList(PaymentPlanForm paymentPlanForm) {
        PageListVO<PaymentPlan> pageListVO = paymentPlanService.searchList(paymentPlanForm);
        //id转name
        String allCode = saleCommonService.getAllCode();
        List<PaymentPlan> detailList = pageListVO.getList();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                detailList = super.translateIdToName(detailList,allCodeList,new String[]{"payMethod,rowId,name","typeId,rowId,name"});
            }
            pageListVO.setList(detailList);
        }

        return getSuccessJson(pageListVO);
    }


    /**
     * @Author jsl
     * @Date 增加回款计划 2018/8/9
     * @Description
     **/
    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody PaymentPlan paymentPlan){
        String token = RequestContext.get().getToken();
        User user = redisService.getLoginUserByToken(token);

        //根据userName 转 stf_base_info row_id
        String baseInfo = staffService.getBaseInfo(user.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        paymentPlan.setCreatedBy(createdBy);

        if(paymentPlanService.add(paymentPlan)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 删除回款计划 2018/8/9
     * @Description
     **/
    @PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id){
        if(paymentPlanService.del(id)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 更新 2018/8/13 
     * @Description    
     **/
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody PaymentPlan paymentPlan) {

        //设置更新人
        String token = RequestContext.get().getToken();
        User user = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(user.getUserName());
        Long lastCreatedBy = MethodUtil.getStaffRowId(baseInfo);
        paymentPlan.setLastUpdateBy(lastCreatedBy);

        if(paymentPlanService.update(paymentPlan)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 根据id查询 2018/8/13
     * @Description    
     **/
    @RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        PaymentPlan paymentPlan = paymentPlanService.getById(rowId);
        return getSuccessJson(paymentPlan);
    }

    /**
     * @Author jsl
     * @Date 修改----生成期次 和 可计划的金额 2018/8/13
     * @Description    count 表里的记录
     **/
    @RequestMapping(value = "/getUpdatePeriod/{saleContractId}/{payPlanId}", produces = "application/json;charset=UTF-8")
    public String getUpdatePeriodAndApplicableAmount(@PathVariable("saleContractId") Long saleContractId,@PathVariable("payPlanId") Long payPlanId) {
        PaymentPlan paymentPlan = paymentPlanService.getUpdatePeriodAndApplicableAmount(saleContractId,payPlanId);
        return getSuccessJson(paymentPlan);
    }

    /**
     * @Author jsl
     * @Date 新增----生成期次 和 可计划的金额 2018/8/13
     * @Description    count 表里的记录
     **/
    @RequestMapping(value = "/getAddPeriod/{saleContractId}", produces = "application/json;charset=UTF-8")
    public String getAddPeriodAndApplicableAmount(@PathVariable("saleContractId") Long saleContractId) {
        PaymentPlan paymentPlan = paymentPlanService.getAddPeriodAndApplicableAmount(saleContractId);
        return getSuccessJson(paymentPlan);
    }

}
