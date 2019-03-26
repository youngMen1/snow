package com.snow.snowcore.utils;

import com.snow.snowcore.api.BasicAuthPair;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description basic auth 請求模板
 **/
@Component
public class BasicRestTemplate {
    private RestTemplateBuilder builder;

    public BasicRestTemplate(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    /**
     * 設置
     *
     * @param basicAuthPair
     */
    public BasicRestTemplate withBasicAuthPair(BasicAuthPair basicAuthPair) {
        builder.basicAuthorization(basicAuthPair.getUserName(), basicAuthPair.getPassword());
        return this;
    }

    /**
     * 超時時間
     *
     * @param millSeconds
     * @return
     */
    public BasicRestTemplate withReadTimeout(int millSeconds) {
        builder.setReadTimeout(millSeconds);
        return this;
    }

    /**
     * 鏈接時間
     *
     * @param millSeconds
     * @return
     */
    public BasicRestTemplate withConnectTimeout(int millSeconds) {
        builder.setConnectTimeout(millSeconds);
        return this;
    }

    public RestTemplate build() {
        return builder.build();
    }
}
