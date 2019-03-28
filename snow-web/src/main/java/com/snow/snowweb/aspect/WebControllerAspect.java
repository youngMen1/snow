package com.snow.snowweb.aspect;

import com.snow.snowcore.aspects.SnowMetrics;
import com.snow.snowweb.interceptor.CatInfo;
import com.snow.snowweb.interceptor.ExecutedTimeMetrics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:06
 * @description
 **/
@Aspect
@Component
@EnableAspectJAutoProxy(
        proxyTargetClass = true
)
public class WebControllerAspect {
    private static final Logger log = LoggerFactory.getLogger(WebControllerAspect.class);

    public WebControllerAspect() {
    }

    @Around("(@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)  || @annotation(org.springframework.web.bind.annotation.PutMapping)) && @annotation(io.swagger.annotations.ApiOperation)")
    public Object webControllerAspectAround(ProceedingJoinPoint pjp) throws Throwable {
        ExecutedTimeMetrics executedTimeMetrics = new ExecutedTimeMetrics();

        Object var4;
        try {
            executedTimeMetrics.setStartDateTime(LocalDateTime.now());
            executedTimeMetrics.setApiName(pjp.getSignature().getName());
            CatInfo.addArgs(pjp.getArgs());
            Object ret = pjp.proceed();
            CatInfo.addResponse(ret);
            var4 = ret;
        } catch (Exception var8) {
            CatInfo.addException(var8);
            throw var8;
        } finally {
            executedTimeMetrics.setEndDateTime(LocalDateTime.now());
            CatInfo.addControllerExecutedTime(executedTimeMetrics);
        }

        return var4;
    }

    @Around("@annotation(snowMetrics)")
    public void around(ProceedingJoinPoint pjp, SnowMetrics snowMetrics) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        String apiName = snowMetrics.apiName();
        if (!StringUtils.hasLength(apiName)) {
            apiName = pjp.getSignature().getName();
        }

        Exception e = null;

        try {
            pjp.proceed();
        } catch (Exception var10) {
            e = var10;
            throw var10;
        } finally {
            CatInfo.addApiRequestMetrics(new ExecutedTimeMetrics(start, LocalDateTime.now(), apiName, e));
        }

    }
}
