package com.champlink.gateway.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.system.Resource;
import com.champlink.common.domain.system.User;
import com.champlink.common.util.cache.RedisService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AuthFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private RedisService redisService;

    private static final String ERROR_UNAUTHORIZED = "{\"data\":[],\"msg\":\"request is unauthorized!\",\"rescode\":498,\"result\":\"fail\"}";
    // 会话过期返回的错误信息
    private static final String ERROR_SCRIPT = "{\"data\":[],\"msg\":\"会话已过期，请重新登录!\",\"rescode\":499,\"result\":\"fail\"}";

    // 会话过期返回的状态
    private static final int SESSION_INVALID_STATUS = 499;
    private static final int UNAUTHORIZED_STATUS = 498;

    @Value("#{'${filter.exclude.login}'.split(';')}")
    private Set<String> excludeLoginPath;

    @Value("#{'${filter.exclude.auth}'.split(';')}")
    private Set<String> excludeAuthPath;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        HttpServletResponse response = rc.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            // 过滤 prelight request （focus post put delete)
            String method = request.getMethod();
            if (method.equalsIgnoreCase("OPTIONS")) {
                return null;
            }

            String requestUrl = request.getRequestURI();
            log.info("进入授权访问:" + requestUrl);
            // 白名单直接放行
            if (excludeFilter(request, response)) {
                log.info("Exclude");
                return null;
            }

            String token = Optional.ofNullable(request.getParameter("token")).filter(StringUtils::isNotBlank).orElseGet(() -> request.getHeader("token"));
            User loginUser = redisService.readValue(Constant.sessionKeyStart + token, User.class);
            if (loginUser == null) {
                response.setStatus(SESSION_INVALID_STATUS);
                rc.setSendZuulResponse(false);
                response.getWriter().write(ERROR_SCRIPT);
                rc.setResponse(response);
                return null;
            }

            // admin 直接放行
            if ("admin".equals(loginUser.getUserName().toLowerCase())) {
                log.info("admin");
                return null;
            }
            List<Resource> userResourceList = redisService.readValue(Constant.CACHE_NAME_USER_RESOURCE_LIST + loginUser.getRowId(), List.class);
            if (userResourceList == null) {
                response.setStatus(SESSION_INVALID_STATUS);
                rc.setSendZuulResponse(false);
                response.getWriter().write(ERROR_SCRIPT);
                rc.setResponse(response);
                return null;
            }
//            boolean hasAuth = false;
//            for (Resource res : userResourceList) {
//                if (requestUrl.startsWith(res.getAccessUrl())) {
//                    hasAuth = true;
//                    break;
//                }
//            }
//            if (!hasAuth) {
//                response.setStatus(UNAUTHORIZED_STATUS);
//                rc.setSendZuulResponse(false);
//                response.getWriter().write(ERROR_UNAUTHORIZED);
//                rc.setResponse(response);
//                return null;
//            }


            return null;
        } catch (Exception e) {
            log.error("Response IO 异常!");
            log.error(e.getMessage());
            e.printStackTrace();

            try {
                response.setStatus(UNAUTHORIZED_STATUS);
                rc.setSendZuulResponse(false);
                response.getWriter().write(ERROR_UNAUTHORIZED);
                rc.setResponse(response);
                return null;
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    private boolean excludeFilter(HttpServletRequest request, HttpServletResponse response) {
        String urlPath = request.getRequestURI();
        if (!urlPath.startsWith("/")) {
            urlPath = "/" + urlPath;
        }
        excludeAuthPath.addAll(excludeLoginPath);
        for (String authPath : excludeAuthPath) {
            if (urlPath.startsWith(authPath)) {
                return true;
            }
        }
        return false;
    }

}
