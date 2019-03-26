package com.snow.snowcore.aspects;

import com.snow.snowcore.utils.RetryConfig;
import com.snow.snowcore.utils.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 攔截器
 **/
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class SnowRetryAspect {

    public SnowRetryAspect() {
        log.debug("RetryerAspect已被加載");
    }

    @Around(value = "@annotation(snowRetry)")
    public Object around(ProceedingJoinPoint pjp, SnowRetry snowRetry) throws IllegalAccessException, InstantiationException {

        Object result = RetryUtil.retry(() -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                return null;
            }
        }, (RetryConfig) snowRetry.config().newInstance());

        return result;
    }
}
