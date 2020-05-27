package com.champlink.org.controller.position;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.org.position.GradeForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.service.call.StaffService;
import com.champlink.org.service.position.GradeService;

@RestController
@RequestMapping("/grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Grade grade) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        grade.setCreatedBy(createdBy);
        grade.setCreatedTime(new Date());
        gradeService.add(grade);
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
        gradeService.del(id);
        return getSuccessJson();
    }

    /**
     * 修改
     * 
     * @param AddEditOrgForm
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(Grade grade) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        grade.setLastUpdateBy(createdBy);
        grade.setLastUpdateTime(new Date());
        if (gradeService.update(grade)) {
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
    public String pageList(GradeForm gradeForm) {

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        gradeForm.setStaffIdList(staffIdList);

        if (gradeForm.getGradeName() != null) {
            gradeForm.setGradeName(gradeForm.getGradeName().trim());
        }

        PageListVO pageListVO = gradeService.pageList(gradeForm);
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
        Grade grade = gradeService.findOne(id);
        return getSuccessJson(grade);
    }

    @RequestMapping(value = "/getByType", produces = "text/json;charset=UTF-8")
    public String getByPositionType() {
        List<Grade> list = gradeService.getByType();
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getAllGrade", produces = "text/json;charset=UTF-8")
    public String getAllGrade() {
        List<Grade> list = gradeService.getAllGrade();
        return getSuccessJson(list);
    }

}
