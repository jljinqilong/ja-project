package com.champlink.emolument.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.emolument.EltAccumulationFund;
import com.champlink.common.domain.emolument.ExcelBean.EltAccumulationFundExcelBean;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltAccumulationFundForm;
import com.champlink.common.service.call.OrgServiceFacade;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltAccumulationFundService;

@RestController
@RequestMapping("/eltAccumulationFund")
public class EltAccumulationFundController extends BaseController {

    @Autowired
    private EltAccumulationFundService eltAccumulationFundService;

    @Autowired
    private OrgServiceFacade orgServiceFacade;

    /**
     * 新增公积金规则
     *
     * @param eltAccumulationFund
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(EltAccumulationFund eltAccumulationFund) throws ParseException {
        if (eltAccumulationFundService.addEltAccumulationFund(eltAccumulationFund)) {
            return getSuccessJson();
        }
        AppException.create(200000);
        return getFailJson();

    }

    /**
     * 删除公积金规则
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (eltAccumulationFundService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @author natyu
     * @date 2018年8月3日 上午10:15:02
     */
    @RequestMapping(value = "/beachDel/{ids}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("ids") Long[] ids) {
        if (eltAccumulationFundService.beachDel(ids)) {
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
        if (eltAccumulationFundService.empty()) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新公积金规则
     *
     * @param eltAccumulationFund
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(EltAccumulationFund eltAccumulationFund) {
        //设置更新时间
        eltAccumulationFund.setLastUpdateTime(new Timestamp(new Date().getTime()));
        if (eltAccumulationFundService.update(eltAccumulationFund)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 公积金规则列表
     *
     * @param eltAccumulationFundForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltAccumulationFundForm eltAccumulationFundForm) {
        PageListVO pageListVO = eltAccumulationFundService.pageList(eltAccumulationFundForm);
        List<EltAccumulationFund> list = pageListVO.getList();
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

    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        EltAccumulationFund eltAccumulationFund = eltAccumulationFundService.findOne(id);
        return getSuccessJson(eltAccumulationFund);
    }

    /**
     * 为员工添加或者修改规则
     *
     * @param staffId
     * @param ruleId
     * @return
     */
    @RequestMapping(value = "/addOrUpdateStaffRule")
    public String addOrUpdateStaffRule(@RequestParam("staffId") String staffId, @RequestParam("ruleId") Long ruleId, @RequestParam("id") int id) {

        if (eltAccumulationFundService.addRule(staffId, ruleId, id)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

    /**
     * 删除员工得规则
     *
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/deleteStaffRule")
    public String deleteByStaffId(@PathVariable("staffId") Long staffId) {

        if (eltAccumulationFundService.deleteByStaffId(staffId)) {
            return getSuccessJson();
        } else {
            return getErrorJson();
        }
    }

    /**
     * 导入公积金Excel
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/importExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<EltAccumulationFundExcelBean> list = new ArrayList<EltAccumulationFundExcelBean>();
        ImportExcelUtils.export(request, EltAccumulationFundExcelBean.class, list);
        List<EltAccumulationFundExcelBean> exportErrList = eltAccumulationFundService.importExcel(list);
        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_ACCUMULATION_INFO + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ACCUMULATION_INFO + redisKey));
        }
    }

    /**
     * 导出公积金错误信息
     * @param request
     * @param response
     * @param lang
     * @param redisKey
     * @throws Exception
     */
    @RequestMapping(value = "/exportErrExcel")
    public void exportErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ACCUMULATION_INFO + redisKey));
        // 查询数据
        List<EltAccumulationFundExcelBean> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_ACCUMULATION_INFO + redisKey)),
            EltAccumulationFundExcelBean.class);
        eltAccumulationFundService.exportErrExcel(response, list, lang);
    }

}
