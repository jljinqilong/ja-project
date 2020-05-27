package com.champlink.common.web.ctx;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.User;
import com.champlink.common.service.call.StaffServiceFacade;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.context.ContextUtils;
import com.champlink.common.util.exception.TimeOutException;

/**
 * 请求上下文
 * @author fishcat
 * @date 2018/8/6 上午9:10
 */
public final class RequestContext {

    private String token;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private User loginUser;
    private BaseInfo baseInfo;

    // 会话过期返回的错误信息
    private static final String ERROR_SCRIPT = "{\"data\":[],\"msg\":\"会话已过期，请重新登录!\",\"rescode\":499,\"result\":\"fail\"}";
    // 会话过期返回的状态
    private static final int SESSION_INVALID_STATUS = 499;

    private static ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<>();

    public static RequestContext begin(HttpServletRequest request, HttpServletResponse response) {
        RequestContext context = new RequestContext();

        // 基础信息
        context.token = Optional.ofNullable(request.getParameter("token")).filter(StringUtils::isNotBlank).orElseGet(() -> request.getHeader("token"));
        context.request = request;
        context.response = response;

        Optional.ofNullable(context.token).filter(StringUtils::isNotBlank).ifPresent(token -> {
            // 账号信息
            RedisService redisService = ContextUtils.getBean(RedisService.class);
            context.loginUser = redisService.getLoginUserByToken(token);

            // redis 缓存失效则不加载员工信息
            if (context.loginUser != null) {
                // 员工信息
                StaffServiceFacade staffServiceFacade = ContextUtils.getBean(StaffServiceFacade.class);
                String result = staffServiceFacade.getBaseInfo(context.getUserName());
                Optional.ofNullable(result).filter(StringUtils::isNotBlank).ifPresent(item -> {
                    JSONObject parseObject = JSONObject.parseObject(item);
                    if ((Integer) parseObject.get("code") == 200) {
                        context.baseInfo = JSONObject.parseObject(parseObject.getString("data"), BaseInfo.class);
                    }
                });
            }
        });

        contextThreadLocal.set(context);
        return context;
    }

    public static RequestContext get() {
        return contextThreadLocal.get();
    }

    public void end() {
        contextThreadLocal.remove();
    }

    public String getToken() {
        return token;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public String getUserName() {
        return Optional.ofNullable(loginUser).map(User::getUserName).orElse(null);
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public String getStaffName() {
        return Optional.ofNullable(baseInfo).map(BaseInfo::getStaffName).orElse(null);
    }

    public String getStaffNo() {
        return Optional.ofNullable(baseInfo).map(BaseInfo::getStaffNo).orElse(null);
    }

    public Long getStaffId() {
        return Optional.ofNullable(baseInfo).map(BaseInfo::getRowId).orElse(null);
    }

    public Long getBaseId() {
        return Optional.ofNullable(baseInfo).map(BaseInfo::getBaseId).orElse(null);
    }

    public Long getDeptId() {
        return Optional.ofNullable(baseInfo).map(BaseInfo::getDeptId).orElse(null);
    }

    /**
     * 获取当前登录人数据权限返回staffIdList
     * 
     * @author natyu
     * @date 2018年9月19日 下午2:53:38
     * @return
     */
    public List<Long> getRoleAuthorityStaffIdList() {
        RedisService redisService = ContextUtils.getBean(RedisService.class);
        List<Long> staffIdList = redisService.readValue(Constant.dataAuthStaff + token, List.class);
        if (staffIdList == null || staffIdList.size() == 0) {
            TimeOutException.create(ERROR_SCRIPT);
        }
        return staffIdList;
    }

    /**
     * 获取当前登录人数据权限返回deptIdList
     * 
     * @author natyu
     * @date 2018年9月19日 下午2:53:38
     * @return
     */
    public List<Long> getRoleAuthorityDeptIdList() {
        RedisService redisService = ContextUtils.getBean(RedisService.class);
        List<Long> deptIdList = redisService.readValue(Constant.dataAuthDept + token, List.class);
        if (deptIdList == null || deptIdList.size() == 0) {
            TimeOutException.create(ERROR_SCRIPT);
        }
        return deptIdList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
