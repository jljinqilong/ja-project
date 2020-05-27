package com.champlink.system.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.Resource;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.system.UserForm;
import com.champlink.common.util.encrypt.MD5Util;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.system.service.UserService;
import com.champlink.system.service.call.StaffService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    /**
     * 新增用户
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(User user) {
        if (StringUtils.isNotEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getPassword().trim())) {
            user.setPassword(MD5Util.getMD5Code(user.getPassword()));
        } else {
            AppException.create(200004);
        }
        if (userService.add(user)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 批量添加
     * 
     * @param list
     * @return
     */
    @RequestMapping(value = "/batchadd", produces = "text/json;charset=UTF-8")
    public String batchAdd(@RequestParam("list") List<String> list) {
        if (userService.batchAdd(list)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 删除用户
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", produces = "text/json;charset=UTF-8")
    public String del(@RequestParam("userIds") String userIds) {
        if (userService.delByIds(userIds)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 根据用户名删除用户
     * 
     * @param userName
     * @return
     */
    @RequestMapping(value = "/delByUserName/{userName}")
    public String delByUserName(@PathVariable("userName") String userName) {
        if (userService.delByUserName(userName)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更改用户状态
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/change/{id}", produces = "text/json;charset=UTF-8")
    public String change(@PathVariable("id") Long id, @RequestParam("status") int status) {
        if (userService.changeStatus(id, status)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 根据用户名更改用户状态
     * 
     * @author natyu
     * @date 2018年8月15日 上午10:02:25
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/changeByUserName")
    public String changeByUserName(@RequestParam("userName") String userName, @RequestParam("status") int status) {
        if (userService.changeByUserName(userName, status)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 根据userNameList更改用户状态
     * 
     * @author natyu
     * @date 2018年8月15日 上午10:02:25
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/changeByUserNameList")
    public String changeByUserNameList(@RequestParam("userNameList") List<String> userNameList, @RequestParam("status") int status) {
        if (userService.changeByUserNameList(userNameList, status)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新用户
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(User user) {
        User oldUser = userService.getById(user.getRowId());

        if (StringUtils.isNotEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getPassword().trim())) {
            if (user.getPassword().equals(oldUser.getPassword())) {
                user.setPassword(null);
            } else {
                user.setPassword(MD5Util.getMD5Code(user.getPassword()));
            }
        } else {
            AppException.create(200004);
        }
        if (userService.update(user)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        User user = userService.getById(rowId);
        return getSuccessJson(user);
    }

    /**
     * 用户列表
     * 
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(UserForm userForm) {
        //
        List<String> staffNoList = null;
        String token = RequestContext.get().getToken();
        String staffNoBystaffIds = staffService.getStaffNoBystaffIds(token);
        if (staffNoBystaffIds != null) {
            JSONObject parseObject = JSONObject.parseObject(staffNoBystaffIds);
            if ((Integer) parseObject.get("code") == 200) {
                staffNoList = JSONObject.parseArray(parseObject.getString("data"), String.class);
                userForm.setStaffNoList(staffNoList);
            }
        }

        PageListVO<User> pageListVO = userService.pageList(userForm);
        return getSuccessJson(pageListVO);
    }

    /**
     * 获取当前登录用户信息
     * 
     * @param token
     * @param force
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/get/current", produces = "text/json;charset=UTF-8")
    public String getById(@RequestParam("token") String token, @RequestParam(value = "force", defaultValue = "false") boolean force) {
        User user = redisService.readValue(Constant.sessionKeyStart + token, User.class);
        if (null != user) {
            if (force) {
                user = userService.getById(user.getRowId());
                redisService.writeValue(Constant.sessionKeyStart + token, user, Constant.sessionTimeout * 60);
            }
            user.setPassword(null);
            List<Resource> userResList = redisService.readValue(Constant.CACHE_NAME_USER_RESOURCE_LIST + user.getRowId(), List.class);
            JSONObject json = new JSONObject();
            json.put("user", user);
            json.put("res", userResList);
            return getSuccessJson(json);
        }
        return getFailJson();
    }

    /**
     * 修改密码
     * 
     * @author natyu
     * @date 2018年9月19日 下午7:02:19
     * @param userForm
     * @return
     */
    @PostMapping(value = "/changePassword")
    public String changePassword(@RequestBody UserForm userForm) {
        userService.changePassword(userForm);
        return getSuccessJson();
    }

    /**
     * 根据原用户名修改为新用户名
     * 
     * @author natyu
     * @date 2018年9月26日 上午11:57:23
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/updateUserName")
    public String updateUserName(@RequestParam("oldUserName") String oldUserName, @RequestParam("newUserName") String newUserName) {
        userService.updateUserName(oldUserName, newUserName);
        return getSuccessJson();
    }

}
