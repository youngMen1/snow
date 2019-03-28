package com.snow.snowweb.config;

import com.snow.snowweb.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:04
 * @description
 **/
@Configuration
public class ServletConfig {
    public ServletConfig() {
    }

    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new RequestFilter());
        registrationBean.addUrlPatterns(new String[]{"/api/*"});
        return registrationBean;
    }
}
