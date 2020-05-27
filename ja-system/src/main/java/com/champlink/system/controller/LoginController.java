package com.champlink.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.User;
import com.champlink.common.util.encrypt.MD5Util;
import com.champlink.common.util.exception.AppException;
import com.champlink.system.service.ResourceService;
import com.champlink.system.service.RoleService;
import com.champlink.system.service.UserService;
import com.champlink.system.service.call.StaffService;

@RestController
public class LoginController extends BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RoleService roleService;

    /**
     * 登录
     * 
     * @param userName
     * @param password
     * @param request
     * @param resp
     * @return
     */
    @RequestMapping(value = "/login")
    public String doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse resp) {
        User user = userService.getByName(userName);
        if (user == null) {
            return getCustomJson(10, "login_user_no_exist");
        }
        if (Constant.DEL_FLAG_INVALID == user.getStatus()) {
            return getCustomJson(12, "login_user_is_forbid");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return getCustomJson(13, "login_user_pwd_null");
        }
        if (!user.getPassword().equals(MD5Util.getMD5Code(password))) {
            return getCustomJson(11, "login_pass_wrong");
        }
        if (!user.getUserName().equals(Constant.ADMIN)) {
            String jsonStr = staffService.getBaseInfoByStaffNo(user.getUserName());
            if (StringUtils.isNotEmpty(jsonStr)) {
                JSONObject parseObject = JSONObject.parseObject(jsonStr);
                if ((Integer) parseObject.get("code") == 200) {
                    BaseInfo baseInfo = JSONObject.parseObject(parseObject.getString("data"), BaseInfo.class);
                    if (baseInfo == null) {
                        AppException.create(200006);
                    }
                } else {
                    AppException.create(200006);
                }
            } else {
                AppException.create(200006);
            }
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        resourceService.getAllListByUid(user.getRowId());
        List<Long> staffIds = roleService.searchRoleDataByUserId(user.getRowId(), token);
        redisService.writeValue(Constant.dataAuthStaff + token, staffIds, Constant.sessionTimeout * 60);
        redisService.writeValue(Constant.sessionKeyStart + token, user, Constant.sessionTimeout * 60);
        return getSuccessJson(token);
    }

    /**
     * 退出
     * 
     * @param token
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(@RequestParam(value = "token", required = false) String token, HttpServletResponse response) throws IOException {
        //        String forword = "";
        //        String path = "";
        //        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        //        ServletContext servletContext = webApplicationContext.getServletContext();
        //        path = servletContext.getContextPath();
        //        FilterRegistration filter = servletContext.getFilterRegistration("CASTicketValidator");
        //        if (filter != null) { //单点登录退出
        //            String casServerUrlPrefix = filter.getInitParameter("casServerUrlPrefix");
        //            String serverName = filter.getInitParameter("serverName");
        //            forword = casServerUrlPrefix + "logout?service=" + serverName + path;
        //            System.err.println(forword);
        //            response.sendRedirect(forword);
        //        } else if (token != null) {
        User user = redisService.readValue(Constant.sessionKeyStart + token, User.class);
        if (null != user) {
            boolean result = redisService.remove(Constant.sessionKeyStart + token);
            if (!result) {
                log.error("退出系统失败，用户编号:" + user.getUserName());
            }
        }
        //        }
        return getSuccessJson();
    }

}
