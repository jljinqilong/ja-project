package com.champlink.emolument.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.form.emolument.EltPerformanceForm;
import com.champlink.common.form.emolument.ImportEltPerformanceForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.service.EltPerformanceService;

@RestController
@RequestMapping("/eltPerformance")
public class EltPerformanceController extends BaseController {

    @Autowired
    private EltPerformanceService eltPerformanceService;

    /**
     * 绩效规则列表
     * 
     * @param eltPerformanceForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(EltPerformanceForm eltPerformanceForm) {
        PageListVO pageListVO = eltPerformanceService.pageList(eltPerformanceForm);
        return getSuccessJson(pageListVO);
    }

    /**
     * 文件下载 下载导入模板
     * @date 2018年7月27日 上午10:37:37
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }

    /**
     * 导入
     * 
     * @param request
     * @param response
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<ImportEltPerformanceForm> list = new ArrayList<ImportEltPerformanceForm>();
        ImportExcelUtils.export(request, ImportEltPerformanceForm.class, list);

        List<ImportEltPerformanceForm> exportErrList;
        exportErrList = eltPerformanceService.importExcel(list);

        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_ORG_INFO + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ORG_INFO + redisKey));
        }
    }

    /**
     * 导出错误数据
     * 
     * @param request
     * @param response
     * @param form
     * @param lang
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportErrExcel")
    public void exportErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_ORG_INFO + redisKey));
        // 查询数据
        List<ImportEltPerformanceForm> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_ORG_INFO + redisKey)), ImportEltPerformanceForm.class);
        eltPerformanceService.exportErrExcel(response, list, lang);
    }

}
