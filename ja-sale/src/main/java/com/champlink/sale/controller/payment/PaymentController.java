package com.champlink.sale.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.payment.PaymentPlanDetailForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption 回款记录
 * @Author JSL
 * @Date 2018/8/9 10:55
 * 回款记录明细
 **/
@RequestMapping("/paymentDetail")
@RestController
public class PaymentController extends BaseController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;

    /**
     * @Author jsl
     * @Date 查询回款记录 2018/8/9
     * @Description    
     **/
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String searchList(PaymentPlanDetailForm paymentPlanDetailForm) {
        PageListVO<PaymentPlanDetail> pageListVO = paymentService.searchList(paymentPlanDetailForm);
        //id转name
        String allCode = saleCommonService.getAllCode();
        List<PaymentPlanDetail> detailList = pageListVO.getList();
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
     * @Date 根据id查询回款记录信息 2018/8/9
     * @Description    
     **/
    @RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long id) {
        PaymentPlanDetail paymentPlanDetail = paymentService.get(id);
        //id转name
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                super.translateIdToNameObj(paymentPlanDetail,allCodeList,new String[]{"payMethod,rowId,name","typeId,rowId,name"});
            }
        }
        return getSuccessJson(paymentPlanDetail);
    }


    /**
     * @Author jsl
     * @Date 增加回款记录 2018/8/9
     * @Description
     **/
    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody PaymentPlanDetail paymentPlanDetail){
        String token = RequestContext.get().getToken();
        User user = redisService.getLoginUserByToken(token);

        //根据userName 转 stf_base_info row_id
        String baseInfo = staffService.getBaseInfo(user.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        paymentPlanDetail.setCreatedBy(createdBy);
        
        if(paymentService.add(paymentPlanDetail)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 删除回款记录 2018/8/9
     * @Description    
     **/
    @PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id){
        if(paymentService.del(id)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 修改回款记录明细 2018/8/9
     * @Description
     **/
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody PaymentPlanDetail paymentPlanDetail) {

        //设置更新人
        String token = RequestContext.get().getToken();
        User user = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(user.getUserName());
        Long lastCreatedBy = MethodUtil.getStaffRowId(baseInfo);
        paymentPlanDetail.setLastUpdateBy(lastCreatedBy);

        if(paymentService.update(paymentPlanDetail)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 根据回款计划id查询回款记录 2018/8/14
     * @Description
     **/
    @RequestMapping(value = "/getPaymentDetail/{payPlanId}", produces = "application/json;charset=UTF-8")
    public String getPaymentDetailById(@PathVariable("payPlanId") Long payPlanId) {
        List<PaymentPlanDetail> paymentPlanDetailList = new ArrayList<PaymentPlanDetail>();

        paymentPlanDetailList = paymentService.getPaymentDetailList(payPlanId);
        String allCode = saleCommonService.getAllCode();

        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                paymentPlanDetailList = super.translateIdToName(paymentPlanDetailList,allCodeList,new String[]{
                        "payMethod,rowId,name",
                        "typeId,rowId,name"});
            }

        }


        return getSuccessJson(paymentPlanDetailList);
    }
}
