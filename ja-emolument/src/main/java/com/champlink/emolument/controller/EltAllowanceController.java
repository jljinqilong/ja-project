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
import com.champlink.common.domain.emolument.EltAllowance;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltAllowanceForm;
import com.champlink.common.service.call.OrgServiceFacade;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltAllowanceService;

@RestController
@RequestMapping("/eltAllowance")
public class EltAllowanceController extends BaseController {

    @Autowired
    private EltAllowanceService eltAllowanceService;

    @Autowired
    private OrgServiceFacade orgServiceFacade;

    /**
     * 新增岗位津贴
     * 
     * @param eltAllowance
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(EltAllowance eltAllowance) {
        if (eltAllowanceService.add(eltAllowance)) {
            return getSuccessJson();
        }
        AppException.create(200000);
        return getFailJson();
    }

    /**
     * 删除岗位津贴
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (eltAllowanceService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 批量删除
     * 
     * @author natyu
     * @date 2018年8月3日 上午10:15:02
     * @param ids
     * @return
     */
    @RequestMapping(value = "/beachDel/{ids}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("ids") Long[] ids) {
        if (eltAllowanceService.beachDel(ids)) {
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
        if (eltAllowanceService.empty()) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新岗位津贴
     * 
     * @param eltAllowance
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(EltAllowance eltAllowance) {
        eltAllowance.setLastUpdateTime(new Timestamp(new Date().getTime()));
        if (eltAllowanceService.update(eltAllowance)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 岗位津贴列表
     * 
     * @param eltAllowanceForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltAllowanceForm eltAllowanceForm) {
        PageListVO pageListVO = eltAllowanceService.pageList(eltAllowanceForm);
        List<EltAllowance> list = pageListVO.getList();
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
     * 查询一个岗位津贴
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        EltAllowance eltAllowance = eltAllowanceService.findOne(id);
        return getSuccessJson(eltAllowance);
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

        if (eltAllowanceService.addRule(staffId, ruleId, id)) {
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

        if (eltAllowanceService.deleteRule(ruleId)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

}
