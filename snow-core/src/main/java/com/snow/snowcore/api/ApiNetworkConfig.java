package com.snow.snowcore.api;

import java.lang.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Api 配置
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiNetworkConfig {

    /**
     * 读取超时时间
     *
     * @return
     */
    int readTimeout() default 2000;

    /**
     * 连接超时时间
     *
     * @return
     */
    int connectTimeout() default 1000;

    /**
     * 重試次數
     */
    int retryTime() default 3;

    /**
     * 總的超時時間
     */
    int totalTimeout() default 10000;

}
