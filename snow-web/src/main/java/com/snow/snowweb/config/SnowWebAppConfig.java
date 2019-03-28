package com.snow.snowweb.config;

import com.snow.snowweb.interceptor.CatInfoInterceptor;
import com.snow.snowweb.interceptor.RqContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:07
 * @description
 **/
@Configuration
public class SnowWebAppConfig implements WebMvcConfigurer {
    public SnowWebAppConfig() {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CatInfoInterceptor()).addPathPatterns(new String[]{"/api/**"});
        registry.addInterceptor(new RqContextInterceptor()).addPathPatterns(new String[]{"/api/**"});
    }
}
