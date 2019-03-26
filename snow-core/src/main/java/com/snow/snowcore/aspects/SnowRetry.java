package com.snow.snowcore.aspects;


import com.snow.snowcore.utils.RetryConfig;

import java.lang.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description SnowRetry 註解
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SnowRetry {
    /**
     * 配置
     *
     * @return RetryConfig配置
     */
    Class<?> config() default RetryConfig.class;
}
