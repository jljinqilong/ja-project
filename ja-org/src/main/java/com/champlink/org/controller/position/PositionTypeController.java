package com.champlink.org.controller.position;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.position.PositionType;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.org.position.PositionTypeForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.service.call.StaffService;
import com.champlink.org.service.position.PositionTypeService;

@RestController
@RequestMapping("/positiontype")
public class PositionTypeController extends BaseController {

    @Autowired
    private PositionTypeService positiontypeService;

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(PositionType positiontype) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        positiontype.setCreatedBy(createdBy);
        positiontype.setCreatedTime(new Date());
        positiontypeService.add(positiontype);
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
        if (positiontypeService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 修改
     * 
     * @param AddEditOrgForm
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(PositionType positiontype) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        positiontype.setLastUpdateBy(createdBy);
        positiontype.setLastUpdateTime(new Date());
        positiontypeService.update(positiontype);
        return getSuccessJson();
    }

    /**
     * 列表
     * 
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(PositionTypeForm positiontypeForm) {
        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        positiontypeForm.setStaffIdList(staffIdList);

        if (positiontypeForm.getTypeName() != null) {
            positiontypeForm.setTypeName(positiontypeForm.getTypeName().trim());
        }

        PageListVO pageListVO = positiontypeService.pageList(positiontypeForm);
        return getSuccessJson(pageListVO);
    }

    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        PositionType positiontype = positiontypeService.findOne(id);
        return getSuccessJson(positiontype);
    }

    @RequestMapping(value = "/getByPositionType", produces = "text/json;charset=UTF-8")
    public String getByPositionType() {
        List<PositionType> list = positiontypeService.getByPositionType();
        return getSuccessJson(list);
    }

}
