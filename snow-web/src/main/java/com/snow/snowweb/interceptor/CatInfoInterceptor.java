package com.snow.snowweb.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:09
 * @description
 **/
public class CatInfoInterceptor extends HandlerInterceptorAdapter {
    public CatInfoInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod)handler;
        CatInfo.init(method.getMethod().getDeclaringClass().getName(), method.getMethod().getName());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CatInfo.logAndRemove();
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }
}

