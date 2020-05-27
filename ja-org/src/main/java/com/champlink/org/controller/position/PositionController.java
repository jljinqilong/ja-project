package com.champlink.org.controller.position;

import java.io.IOException;
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
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.org.position.ImportPositionForm;
import com.champlink.common.form.org.position.PositionForm;
import com.champlink.common.form.org.position.SearchPositionForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.service.call.StaffService;
import com.champlink.org.service.position.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(com.champlink.common.form.org.org.PositionForm positionForm) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        positionForm.setCreatedBy(createdBy);
        positionForm.setCreatedTime(new Date());
        if (positionForm.getStaffSize() == null) {
            positionForm.setStaffSize(0);
        }

        positionService.add(positionForm);
        return getSuccessJson();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        positionService.del(id);
        return getSuccessJson();
    }

    /**
     * 修改
     * 
     * @param AddEditOrgForm
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(com.champlink.common.form.org.org.PositionForm positionForm) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        positionForm.setLastUpdateBy(createdBy);
        positionForm.setLastUpdateTime(new Date());
        if (positionForm.getStaffSize() == null) {
            positionForm.setStaffSize(0);
        }
        if (positionService.update(positionForm)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 列表
     * 
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(PositionForm positionForm) {

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        positionForm.setStaffIdList(staffIdList);

        if (positionForm.getPositionName() != null) {
            positionForm.setPositionName(positionForm.getPositionName().trim());
        }
        PageListVO pageListVO = positionService.pageList(positionForm);
        return getSuccessJson(pageListVO);
    }

    /**
     * 查询单个
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        Position position = positionService.findOne(id);
        return getSuccessJson(position);
    }

    @RequestMapping(value = "/getByPositionType", produces = "text/json;charset=UTF-8")
    public String getByPositionType() {
        List<Position> list = positionService.getByPositionType();
        return getSuccessJson(list);
    }

    /**
     * 
     * 导出
     * 
     * @param request
     * @param response
     * @param form
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/exportExcel")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, SearchPositionForm form) throws Exception {

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        form.setStaffIdList(staffIdList);

        positionService.exportExcel(response, form);
        return getSuccessJson();
    }

    /**
     * 
     * 导入错误信息
     * 
     * @param request
     * @param response
     * @param lang
     * @param redisKey
     * @throws Exception
     */
    @RequestMapping(value = "/exportErrExcel")
    public void exportErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_POSITION + redisKey));
        // 查询数据
        List<ImportPositionForm> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_POSITION + redisKey)), ImportPositionForm.class);
        positionService.exportErrExcel(response, list);
    }

    /**
     * 
     * 
     * 导入
     * 
     * @param request
     * @param response
     * @throws IOException
     */

    @RequestMapping(value = "/importExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ImportPositionForm> list = new ArrayList<ImportPositionForm>();
        ImportExcelUtils.export(request, ImportPositionForm.class, list);
        List<ImportPositionForm> exportErrList = positionService.importExcel(list);
        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_POSITION + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_POSITION + redisKey));
        }
    }

    /**
     * 查询所有的职衔
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getAllPosition")
    public String getAllPosition() {

        List<Position> positionList = positionService.getAllPosition();
        return getSuccessJson(positionList);
    }

    /**
     * 查询单个职衔下对应的所有的职等/赋值名称
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/getAllGradeByPosition/{positionId}")
    public String getAllGradeByPosition(@PathVariable("positionId") Long positionId) {

        List<Grade> gradeListByPositionId = positionService.getAllGradeByPosition(positionId);
        return getSuccessJson(gradeListByPositionId);
    }

    @RequestMapping(value = "/getAllRankByPositionAndGrade/{positionId}/{gradeId}")
    public String getAllRankByPositionAndGrade(@PathVariable("positionId") Long positionId, @PathVariable("gradeId") Long gradeId) {

        List<Rank> rankListByPositionIdAndGradeId = positionService.getAllRankByPositionAndGrade(positionId, gradeId);
        return getSuccessJson(rankListByPositionIdAndGradeId);
    }

}
