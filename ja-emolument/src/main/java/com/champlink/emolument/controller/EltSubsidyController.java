package com.champlink.emolument.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.emolument.EltSubsidy;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltSubsidyForm;
import com.champlink.common.service.call.OrgServiceFacade;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltSubsidyService;

@RestController
@RequestMapping("/eltSubsidy")
public class EltSubsidyController extends BaseController {

    @Autowired
    private EltSubsidyService eltSubsidyService;

    @Autowired
    private OrgServiceFacade orgServiceFacade;

    /**
     * 新增补贴规则
     * 
     * @param eltSubsidy
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(EltSubsidy eltSubsidy) {
        if (eltSubsidyService.add(eltSubsidy)) {
            return getSuccessJson();
        }
        AppException.create(200000);
        return getFailJson();
    }

    /**
     * 删除补贴规则
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (eltSubsidyService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/beachDel/{ids}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("ids") Long[] ids) {
        if (eltSubsidyService.beachDel(ids)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 清空
     * @return
     */
    @RequestMapping(value = "/empty", produces = "text/json;charset=UTF-8")
    public String empty() {
        if (eltSubsidyService.empty()) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新补贴规则
     * 
     * @param eltSubsidy
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(EltSubsidy eltSubsidy) {
        //更新时间
        eltSubsidy.setLastUpdateTime(new Date());
        if (eltSubsidyService.update(eltSubsidy)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 补贴规则列表
     * 
     * @param eltSubsidyForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltSubsidyForm eltSubsidyForm) {
        PageListVO pageListVO = eltSubsidyService.pageList(eltSubsidyForm);
        List<EltSubsidy> list = pageListVO.getList();
        String allOrg = orgServiceFacade.baseList();
        if (allOrg != null) {
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                List<Org> allBaseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                list = super.translateIdToName(list, allBaseList, new String[] {"baseId,rowId,baseOrDeptName"});
            }
        }
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    /**
     * 查询一个补贴规则
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        EltSubsidy eltSubsidy = eltSubsidyService.findOne(id);
        return getSuccessJson(eltSubsidy);
    }

    /**
     * 为员工添加规则
     *
     * @param staffId
     * @param ruleId
     * @return
     */
    @RequestMapping(value = "/addRule")
    public String addRule(@RequestParam("staffId") String staffId, @RequestParam("ruleId") Long ruleId, @RequestParam("id") int id) {
        if (eltSubsidyService.addRule(staffId, ruleId, id)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

    /**
     * 删除员工得规则
     *
     * @param ruleId
     * @return
     */
    @RequestMapping(value = "/deleteRule")
    public String deleteRule(@PathVariable("ruleId") Long ruleId) {

        if (eltSubsidyService.deleteRule(ruleId)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

}
