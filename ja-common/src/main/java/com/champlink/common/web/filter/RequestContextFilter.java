package com.champlink.common.web.filter;

import com.champlink.common.web.ctx.RequestContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 上下文过滤器
 * <p>
 *     用于每一台微服务获取上下文问题，前提必须通过网关的验证
 * </p>
 * @author fishcat
 * @date 2018/8/6 上午9:21
 */
public class RequestContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RequestContext ctx = null;
        try {
            ctx = RequestContext.begin(request, response);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (ctx != null) ctx.end();
        }

    }

    @Override
    public void destroy() {

    }
}
