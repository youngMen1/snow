package com.snow.snowcore.aspects;

import com.snow.snowcore.web.CatInfo;
import com.snow.snowcore.web.ExecutedTimeMetrics;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description API請求執行器
 **/
@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebControllerAspect {

    /**
     * 針對Controller 的攔截
     *
     * @param pjp 切點
     * @return 執行結果
     * @throws Throwable 異常
     */
    @Around("(@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)  " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)) " +
            "&& @annotation(io.swagger.annotations.ApiOperation)")
    public Object webControllerAspectAround(ProceedingJoinPoint pjp) throws Throwable {

        var executedTimeMetrics = new ExecutedTimeMetrics();

        try {
            executedTimeMetrics.setStartDateTime(LocalDateTime.now());
            executedTimeMetrics.setApiName(pjp.getSignature().getName());

            CatInfo.addArgs(pjp.getArgs());

            var ret = pjp.proceed();

            CatInfo.addResponse(ret);

            return ret;

        } catch (Exception ex) {
            CatInfo.addException(ex);
            throw ex;
        } finally {
            executedTimeMetrics.setEndDateTime(LocalDateTime.now());

            CatInfo.addControllerExecutedTime(executedTimeMetrics);
        }
    }

    /**
     * API请求的执行时间
     *
     * @param pjp          執行點
     * @param snowMetrics 性能切點
     * @throws Throwable 異常
     */
    @Around(value = "@annotation(snowMetrics)")
    public void around(ProceedingJoinPoint pjp, SnowMetrics snowMetrics) throws Throwable {

        var start = LocalDateTime.now();

        var apiName = snowMetrics.apiName();

        if (!StringUtils.hasLength(apiName)) {
            apiName = pjp.getSignature().getName();
        }

        Exception e = null;
        try {
            pjp.proceed();
        } catch (Exception ex) {
            e = ex;
            throw ex;
        } finally {
            CatInfo.addApiRequestMetrics(new ExecutedTimeMetrics(start, LocalDateTime.now(), apiName, e));
        }
    }
}
