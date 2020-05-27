package com.champlink.staff.controller.baseInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import com.champlink.common.domain.staff.baseInfo.AdjustmentWork;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.staff.baseInfo.ImportAdjustmentWork;
import com.champlink.common.form.staff.baseInfo.SearchAdjustmentWork;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.service.baseInfo.AdjustmentWorkService;
import com.champlink.staff.service.baseInfo.BaseInfoService;

@RestController
@RequestMapping("/adjustmentWork")
public class AdjustmentWorkController extends BaseController {

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private AdjustmentWorkService adjustmentWorkService;

    //    @RequestMapping(value = "/addOrEdit")
    //    public String addAdjustmentWork(AdjustmentWork adjustmentWork) {
    //        if (adjustmentWorkService.addAdjustmentWork(adjustmentWork)) {
    //            return getSuccessJson();
    //        }
    //        return getFailJson();
    //    }

    /**
     * 员工离职
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/staffLeaveOffice")
    public String staffLeaveOffice(SearchBaseInfoForm form) {
        if (adjustmentWorkService.staffLeaveOffice(form)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 员工异动
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/addStaffMove")
    public String addStaffMove(AdjustmentWork adjustmentWork) {
        if (adjustmentWorkService.addStaffMove(adjustmentWork)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delAdjustmentWorkListByStaffId(@PathVariable Long id) {
        if (adjustmentWorkService.delAdjustmentWorkListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    //重新雇佣/返聘   --日期查询
    @RequestMapping(value = "/getAdjustmentWorkList/{staffId}/{changeType}")
    public String getAdjustmentWorkList(@PathVariable Long staffId, @PathVariable String changeType) {
        List<AdjustmentWork> list = adjustmentWorkService.getAdjustmentWorkList(staffId, changeType);
        return getSuccessJson(list);
    }

    //借调/外派 --时间查询
    @RequestMapping(value = "/getAdjustmentBy/{staffId}/{changeType}")
    public String getAdjustmentBy(@PathVariable Long staffId, @PathVariable String changeType) {
        List<AdjustmentWork> list = adjustmentWorkService.getAdjustmentBy(staffId, changeType);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/list")
    public String searchAdjustmentWorkList(SearchAdjustmentWork searchAdjustmentWork) {

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        searchAdjustmentWork.setStaffIdList(staffIdList);

        PageListVO<AdjustmentWork> pageListVO = adjustmentWorkService.pageList(searchAdjustmentWork);
        List<Long> staffIds = pageListVO.getList().stream().map(contract -> contract.getStaffId()).collect(Collectors.toList());
        List<BaseInfo> baseInfoList = baseInfoService.getBaseInfoByIds(staffIds);
        List<AdjustmentWork> list = super.translateIdToName(pageListVO.getList(), baseInfoList, new String[] {"staffId,rowId,staffName;staffNo"});
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        AdjustmentWork adjustmentWork = adjustmentWorkService.getByRowId(id);
        return getSuccessJson(adjustmentWork);
    }

    /**
     * 员工异动
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/updateStaffMove")
    public String updateStaffMove(AdjustmentWork adjustmentWork) {
        if (adjustmentWorkService.updateStaffMove(adjustmentWork)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 
     * 批量离职
     * 
     * @author jinlin.tang
     * @date 2018年10月30日 下午4:54:35
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/importExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ImportAdjustmentWork> list = new ArrayList<ImportAdjustmentWork>();
        ImportExcelUtils.export(request, ImportAdjustmentWork.class, list);
        List<ImportAdjustmentWork> exportErrList = adjustmentWorkService.importExcel(list);
        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_ADJUSTMENTWORK + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ADJUSTMENTWORK + redisKey));
        }
    }

    /**
     * 
     * 导出错误数据
     * 
     * @author jinlin.tang
     * @date 2018年10月31日 下午2:03:08
     * @param request
     * @param response
     * @param lang
     * @param redisKey
     * @throws Exception
     */
    @RequestMapping(value = "/exportErrExcel")
    public void exportErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ADJUSTMENTWORK + redisKey));
        // 查询数据
        List<ImportAdjustmentWork> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_ADJUSTMENTWORK + redisKey)), ImportAdjustmentWork.class);
        adjustmentWorkService.exportErrExcel(response, list, lang);
    }

    /**
     * 
     * 下载模板
     * 
     * @author jinlin.tang
     * @date 2018年10月31日 下午2:03:26
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }

}