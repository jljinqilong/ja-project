package com.champlink.org.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.OrgAllBackup;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.org.org.ImportOrgAndOrgInfoForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.service.OrgService;
import com.champlink.org.service.call.StaffService;

@RestController
@RequestMapping("/org")
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    @Autowired
	private StaffService staffService;
    
    /**
     * 新增组织架构
     * 
     * @param Org
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(OrgAndOrgInfo orgAndOrgInfo) {
    	//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);


    	orgAndOrgInfo.setCreatedBy(createdBy);
    	orgAndOrgInfo.setCreatedTime(new Date());
        orgService.add(orgAndOrgInfo);
        return getSuccessJson();
    }

    /**
     * 删除组织架构
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {

        orgService.delOrgAndsonOrg(id);
        return getSuccessJson();

    }

    /**
     * 查询组织架构
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String queryOne(@PathVariable("id") Long id) {
        OrgAndOrgInfo obj = orgService.queryOne(id);
        if (obj != null) {
            return getSuccessJson(obj);
        }
        return getFailJson();
    }

    /**
     * 修改组织架构
     * 
     * @param AddEditOrgForm
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(OrgAndOrgInfo orgAndOrgInfo) {
    	//设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        
    	orgAndOrgInfo.setLastUpdateBy(createdBy);
    	orgAndOrgInfo.setLastUpdateTime(new Date());
        orgService.update(orgAndOrgInfo);
        return getSuccessJson();
    }

    /**
     * 查询组织架树,查询全部树，并且树形结构树形都可选中
     * 
     * @return
     */
    @RequestMapping(value = "/findOrgChart", produces = "text/json;charset=UTF-8")
    public String findOrgChart() {

        //获取登录人基地
//        Long baseId = RequestContext.get().getBaseId();
//        JSONArray findOrgChart = orgService.findOrgChart(baseId);

      JSONArray findOrgChart = orgService.findOrgChart();
        if (findOrgChart != null) {
            return getSuccessJson(findOrgChart);
        }

        return getFailJson();
    }
    
    /**
     * 查询组织架树,查询全部树，并且树形结构树形都可选中
     * 
     * @return
     */
    @RequestMapping(value = "/findAllOrgChart", produces = "text/json;charset=UTF-8")
    public String findAllOrgChart() {

        //获取登录人基地
//        Long baseId = RequestContext.get().getBaseId();
//        JSONArray findOrgChart = orgService.findOrgChart(baseId);

      JSONArray findOrgChart = orgService.findAllOrgChart();
        if (findOrgChart != null) {
            return getSuccessJson(findOrgChart);
        }

        return getFailJson();
    }

    /**
     * 查询组织架树,查询全部树但是id下的所有子组织都不可选中
     * 
     * @return
     */
    @RequestMapping(value = "/findOrgChartByDisabled/{id}", produces = "text/json;charset=UTF-8")
    public String findOrgChartByDisabled(@PathVariable("id") Long id) {

        JSONArray findOrgChart = orgService.findOrgChartByDisabled(id);
        return getSuccessJson(findOrgChart);

    }

    /**
     * 判断ids中 如果有重复或者不存在的id 返回isRepeatOrIsAbsent:true, 否则返回id:name的集合
     * @param ids
     * @return
     */
    @RequestMapping(value = "/isRepeatOrIsExist", produces = "text/json;charset=UTF-8")
    public String isRepeatOrIsAbsent(@RequestParam("ids") List<Long> ids) {

        JSONObject result = orgService.isRepeatOrIsAbsent(ids);
        return getSuccessJson(result);

    }

    /**
     * 组织合并
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/orgMerge/{sourceId}/{targetId}", produces = "text/json;charset=UTF-8")
    public String orgMerge(@PathVariable("sourceId") Long sourceId, @PathVariable("targetId") Long targetId) { // sourceId合并到targetId

        boolean isSuccess = orgService.orgMerge(sourceId, targetId);
        return getSuccessJson();

    }

    /**
     * 组织移动
     * @return
     */
    @RequestMapping(value = "/orgMove/{sourceId}/{targetId}", produces = "text/json;charset=UTF-8")
    public String orgMove(@PathVariable("sourceId") Long sourceId, @PathVariable("targetId") Long targetId) { // sourceId移动到targetId下
        boolean orgMove = orgService.orgMove(sourceId, targetId);
        return getSuccessJson();
    }

    /**
     * 
     * @return
     */
    @RequestMapping(value = "/baseList", produces = "text/json;charset=UTF-8")
    public String baseList() {
        // 查询所有的基地
        List<Org> orgList = orgService.getBaseList();
        return getSuccessJson(orgList);
    }

    @RequestMapping(value = "/deptList", produces = "text/json;charset=UTF-8")
    public String deptList() {
        // 查询所有的基地
        List<Org> orgList = orgService.getDeptList();
        return getSuccessJson(orgList);

    }

    @RequestMapping(value = "/getAllOrg")
    public String getAllOrg() {
        return getSuccessJson(orgService.getAllOrg());
    }

    /**
     * 导入
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:20:12
     * @param request
     * @param response
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<ImportOrgAndOrgInfoForm> list = new ArrayList<ImportOrgAndOrgInfoForm>();
        ImportExcelUtils.export(request, ImportOrgAndOrgInfoForm.class, list);

        List<ImportOrgAndOrgInfoForm> exportErrList;
        exportErrList = orgService.importExcel(list);

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
     * @author natyu
     * @date 2018年7月27日 下午3:18:17
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
        List<ImportOrgAndOrgInfoForm> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_ORG_INFO + redisKey)), ImportOrgAndOrgInfoForm.class);
        orgService.exportErrExcel(response, list, lang);
    }

    /**
     * 
     * 导出Excel
     * 
     * @author junqi
     * @date Dec 11, 2014 8:36:20 PM
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang) throws Exception {
        orgService.exportExcel(response);
    }

    /**
     * 备份数据 历史
     * @return
     */
    @RequestMapping(value = "/backUpInfo")
    public String backUpOrgAllInfo() {
        try {
            orgService.backUpOrgAllInfo();
            return getSuccessJson();
        } catch (Exception e) {
            e.printStackTrace();
            return getFailJson();
        }
    }

    /**
     * 查询历史组织架树
     * 
     * @return
     */
    @RequestMapping(value = "/findOrgHistoryChart", produces = "text/json;charset=UTF-8")
    public String findOrgHistoryChart(String date) {

        Date d = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Long> deptIdList = RequestContext.get().getRoleAuthorityDeptIdList();
    	deptIdList.add(1L);
        
        JSONArray findOrgChart = orgService.findOrgHistoryChart(d, deptIdList);

        return getSuccessJson(findOrgChart);

    }

    /**
     * 查询历史组织查询一个 org/findOneOrgHistory/{rowId}/{date}
     * @return
     */
    @RequestMapping(value = "/findOneOrgHistory/{rowId}/{date}", produces = "text/json;charset=UTF-8")
    public String findOrgHistory(@PathVariable("rowId") Long rowId, @PathVariable("date") String date) {
        String str = date;
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        OrgAllBackup findOneByRowId = orgService.findOneByRowId(rowId, d);

        return getSuccessJson(findOneByRowId);
    }

    /**
     * 查询历史组织架构层次图
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/hierarchyHistoryChart/{date}")
    public String hierarchyHistoryChart(@PathVariable("date") String date) {
        String str = date;
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray findOrgHierarchyHistoryChart = orgService.findOrgHierarchyHistoryChart(d);
        if (findOrgHierarchyHistoryChart != null) {
            return getSuccessJson(findOrgHierarchyHistoryChart);
        }
        return getFailJson();
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
     * 获取当前组织架构层次图
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/hierarchyChart")
    public String hierarchyChart() {
        JSONArray findOrgHierarchyChart = orgService.findOrgHierarchyChart();
        if (findOrgHierarchyChart != null) {
            return getSuccessJson(findOrgHierarchyChart);
        }
        return getFailJson();
    }

    /**
     * 根据组织id查询该组织下所有的部门集合
     * @param baseId
     * @return
     */
    @RequestMapping(value = "/getAllSonOrg/{baseId}")
    public String getAllSonOrg(@PathVariable("baseId") Long baseId) {
        List<Org> queryAllSonOrg = orgService.queryAllSonOrg(baseId);

        return getSuccessJson(queryAllSonOrg);
    }

    /**
     * 根据基地id查询下属所有部门树形结构
     * @param baseId
     * @return
     */
    @RequestMapping(value = "/getSonOrgTree/{baseId}")
    public String getSonOrgTree(@PathVariable("baseId") Long baseId) {
        JSONArray SonOrgTree = orgService.getSonOrgTree(baseId);

        return getSuccessJson(SonOrgTree);
    }

}
