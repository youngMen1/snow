package com.snow.snowweb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:08
 * @description
 **/
public class RequestFilter implements Filter {
    public RequestFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof RequestWrapper)) {
            RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest)servletRequest);
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
