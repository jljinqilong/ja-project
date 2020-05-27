package com.champlink.gateway.filter;

import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.system.User;
import com.champlink.common.util.cache.RedisService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class LoginFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private RedisService redisService;

    // 会话过期返回的错误信息
    private static final String ERROR_SCRIPT = "{\"data\":[],\"msg\":\"会话已过期，请重新登录!\",\"rescode\":499,\"result\":\"fail\"}";

    // 会话过期返回的状态
    private static final int SESSION_INVALID_STATUS = 499;

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Value("#{'${filter.exclude.login}'.split(';')}")
    private Set<String> excludePathSet;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        HttpServletResponse response = rc.getResponse();
        response.setCharacterEncoding("UTF-8");

        //        String userCas = request.getRemoteUser();
        //        if (StringUtils.isNotEmpty(userCas)) {//启用单点登录，无需校验，进行系统初始化
        //            request.getSession().setAttribute("user", userCas);
        //
        //        }

        // 过滤 prelight request （focus post put delete)
        String method = request.getMethod();
        if (method.equalsIgnoreCase("OPTIONS")) {
            return null;
        }

        String token = Optional.ofNullable(request.getParameter("token")).filter(StringUtils::isNotBlank).orElseGet(() -> request.getHeader("token"));
        String lang = request.getParameter("lang");

        // 白名单直接放行
        if (excludeFilter(request, response)) {
            log.info("Exclude");
            rc.setRequest(request);
            return null;
        }
        try {
            User user = redisService.readValue(Constant.sessionKeyStart + token, User.class);
            if (user == null) {
                response.setStatus(SESSION_INVALID_STATUS);
                rc.setSendZuulResponse(false);
                response.getWriter().write(ERROR_SCRIPT);
                rc.setResponse(response);
                return null;
            }

            // 重新设置登录的过期时间
            redisService.writeValue(Constant.sessionKeyStart + token, user, Constant.sessionTimeout * 60);
        } catch (Exception e) {
            log.error("Login Filter 异常!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    private boolean excludeFilter(HttpServletRequest request, HttpServletResponse response) {
        String urlPath = request.getRequestURI();
        if (!urlPath.startsWith("/")) {
            urlPath = "/" + urlPath;
        }
        for (String registeredPattern : excludePathSet) {
            if (pathMatcher.match(registeredPattern, urlPath)) {
                return true;
            }
        }
        return false;
    }

}
