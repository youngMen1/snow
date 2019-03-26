package com.snow.snowcore.aspects;

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
 * @description
 **/
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Slf4j
public class IdempotentAspect {

    @Around("@annotation(idempotent)")
    public Object idempotentAround(ProceedingJoinPoint pjp, Idempotent idempotent) throws Throwable {
        return pjp.proceed();
    }
}
