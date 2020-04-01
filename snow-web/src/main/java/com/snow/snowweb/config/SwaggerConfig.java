package com.snow.snowweb.config;

import com.snow.snowcore.config.SwaggerApiInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:07
 * @description
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public SwaggerConfig() {
    }

    @Bean
    public Docket createRestApi(SwaggerApiInfo swaggerApiInfo) {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(new ApiInfo(swaggerApiInfo.getTitle(), swaggerApiInfo.getDescription(), swaggerApiInfo.getVersion(), swaggerApiInfo.getTermsOfServiceUrl(), swaggerApiInfo.getContactName(), "", "")).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SwaggerApiInfo swaggerApiInfo() {
        return SwaggerApiInfo.builder().title("snow").description(".").termsOfServiceUrl("http://fengzhiqiang.top/").version("1.0").build();
    }
}
