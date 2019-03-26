package com.snow.snowcore.aspects;

import java.lang.annotation.*;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 執行時間度量
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SnowMetrics {
    /**
     * api名稱
     * 如果為空，則取方法名
     *
     * @return 名稱
     */
    String apiName() default "";
}
