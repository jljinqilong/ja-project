package com.champlink.emolument.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.emolument.EltSocialSecurity;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltSocialSecurityForm;
import com.champlink.common.service.call.OrgServiceFacade;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltSocialSecurityService;

@RestController
@RequestMapping("/eltSocialSecurity")
public class EltSocialSecurityController extends BaseController {

    @Autowired
    private EltSocialSecurityService eltSocialSecurityService;

    @Autowired
    private OrgServiceFacade orgServiceFacade;

    /**
     * 新增社保规则
     * 
     * @param eltSocialSecurity
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(EltSocialSecurity eltSocialSecurity) {
        if (eltSocialSecurityService.add(eltSocialSecurity)) {
            return getSuccessJson();
        }
        AppException.create(200000);
        return getFailJson();
    }

    /**
     * 删除社保规则
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (eltSocialSecurityService.del(id)) {
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
        if (eltSocialSecurityService.beachDel(ids)) {
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
        if (eltSocialSecurityService.empty()) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新社保规则
     * 
     * @param eltSocialSecurity
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(EltSocialSecurity eltSocialSecurity) {
        eltSocialSecurity.setLastUpdateTime(new Timestamp(new Date().getTime()));
        if (eltSocialSecurityService.update(eltSocialSecurity)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 社保规则列表
     * 
     * @param eltSocialSecurityForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltSocialSecurityForm eltSocialSecurityForm) {
        PageListVO pageListVO = eltSocialSecurityService.pageList(eltSocialSecurityForm);
        List<EltSocialSecurity> list = pageListVO.getList();
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
     * 查询一个社保规则
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        EltSocialSecurity eltSocialSecurity = eltSocialSecurityService.findOne(id);
        return getSuccessJson(eltSocialSecurity);
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

        if (eltSocialSecurityService.addRule(staffId, ruleId, id)) {
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

        if (eltSocialSecurityService.deleteRule(ruleId)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }
}
