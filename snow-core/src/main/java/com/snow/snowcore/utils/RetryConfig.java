package com.snow.snowcore.utils;

import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description SnowRetry 配置
 **/
@Getter
@Setter
@AllArgsConstructor
public class RetryConfig {
    /**
     * 默認
     */
    public static final RetryConfig DEFAULT = new RetryConfig(3, WaitStrategies.fixedWait(2, TimeUnit.SECONDS), 120);

    /**
     * 重試次數
     * 默認為3
     */
    private int retryCount;

    /**
     * 重試策略
     * 默認為固定等待2s
     */
    private WaitStrategy waitStrategy;

    /**
     * 總共的超時時間
     * 默認120s
     */
    private int totalTimeout;

    public RetryConfig() {
        retryCount = 3;
        waitStrategy = WaitStrategies.fixedWait(2, TimeUnit.SECONDS);
        totalTimeout = 120;
    }
}
