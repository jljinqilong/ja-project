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
import com.champlink.common.domain.emolument.EltSalary;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltSalaryForm;
import com.champlink.common.service.call.OrgServiceFacade;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltSalaryService;

@RestController
@RequestMapping("/eltSalary")
public class EltSalaryController extends BaseController {

    @Autowired
    private EltSalaryService eltSalaryService;

    @Autowired
    private OrgServiceFacade orgServiceFacade;

    /**
     * 新增固定工资
     * 
     * @param eltSalary
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(EltSalary eltSalary) {
        if (eltSalaryService.add(eltSalary)) {
            return getSuccessJson();
        }
        AppException.create(200000);
        return getFailJson();
    }

    /**
     * 删除固定工资
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (eltSalaryService.del(id)) {
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
        if (eltSalaryService.beachDel(ids)) {
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
        if (eltSalaryService.empty()) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新固定工资
     * 
     * @param eltSalary
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(EltSalary eltSalary) {
        //更新时间
        eltSalary.setLastUpdateTime(new Timestamp(new Date().getTime()));
        if (eltSalaryService.update(eltSalary)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 固定工资列表
     * 
     * @param eltSalaryForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltSalaryForm eltSalaryForm) {
        PageListVO pageListVO = eltSalaryService.pageList(eltSalaryForm);
        List<EltSalary> list = pageListVO.getList();
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
     * 查询一个固定工资
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        EltSalary eltSalary = eltSalaryService.findOne(id);
        return getSuccessJson(eltSalary);
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

        if (eltSalaryService.addRule(staffId, ruleId, id)) {
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

        if (eltSalaryService.deleteRule(ruleId)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

}
