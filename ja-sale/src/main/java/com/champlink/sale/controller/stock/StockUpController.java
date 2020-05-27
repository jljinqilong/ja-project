package com.champlink.sale.controller.stock;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.stock.StockUp;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.stock.StockForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.stock.StockUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/14 19:24
 **/
@RequestMapping("/stockUp")
@RestController
public class StockUpController extends BaseController {
    @Autowired
    private StockUpService stockUpService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String searchAreaList(StockForm form) {
        PageListVO<StockUp> pageListVO = stockUpService.searchList(form);
        List<StockUp> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        for(StockUp stockUp : list){
            if (allCode != null) {
                JSONObject parseObject = JSONObject.parseObject(allCode);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    list = super.translateIdToName(list,allCodeList,new String[]{
                            "stockAddress,rowId,name",
                            "paymentInfo,rowId,name",
                            "typeA,rowId,name",
                            "deliveryMethod,rowId,name",
                            "customer,rowId,name"});
                }
            }
            pageListVO.setList(list);
        }

        return getSuccessJson(pageListVO);
    }

    /**
     * @Author jsl
     * @Date 增加 2018/8/14
     * @Description
     **/
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String addRegion(@RequestBody StockUp stockUp){
        //取登陆人的信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);
        //设置created_by
        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        stockUp.setCreatedBy(createdBy);

        if(stockUpService.add(stockUp)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 删除 2018/8/14
     * @Description
     **/
    @PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long rowId) {
        if(stockUpService.del(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 根据id查询 2018/8/14
     * @Description
     **/
    @RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        StockUp stockUp = new StockUp();
        stockUp = stockUpService.get(rowId);

        String allCode = saleCommonService.getAllCode();

        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                stockUp = (StockUp) super.translateIdToNameObj(stockUp,allCodeList,new String[]{
                        "stockAddress,rowId,name",
                        "paymentInfo,rowId,name",
                        "typeA,rowId,name",
                        "deliveryMethod,rowId,name"});
            }
        }


        return getSuccessJson(stockUp);
    }


    /**
     * 修改
     * @param
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody StockUp stockUp) {

        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);
        //设置created_by
        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long lastUpdateBy = MethodUtil.getStaffRowId(baseInfo);
        stockUp.setLastUpdateBy(lastUpdateBy);
        if(stockUpService.update(stockUp)){
            return getSuccessJson();
        }
        return getFailJson();
    }
}
