package com.snow.snowweb;

import com.snow.snowcore.api.BasicAuthPair;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:10
 * @description
 **/
@Component
public class BasicRestTemplate {
    private RestTemplateBuilder builder;

    public BasicRestTemplate(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    public BasicRestTemplate withBasicAuthPair(BasicAuthPair basicAuthPair) {
        this.builder.basicAuthorization(basicAuthPair.getUserName(), basicAuthPair.getPassword());
        return this;
    }

    public BasicRestTemplate withReadTimeout(int millSeconds) {
        this.builder.setReadTimeout(millSeconds);
        return this;
    }

    public BasicRestTemplate withConnectTimeout(int millSeconds) {
        this.builder.setConnectTimeout(millSeconds);
        return this;
    }

    public RestTemplate build() {
        return this.builder.build();
    }
}

