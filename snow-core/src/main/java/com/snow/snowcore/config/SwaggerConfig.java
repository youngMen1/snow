package com.snow.snowcore.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Swagger 模板
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(SwaggerApiInfo swaggerApiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(swaggerApiInfo.getTitle(),
                        swaggerApiInfo.getDescription(),
                        swaggerApiInfo.getVersion(),
                        swaggerApiInfo.getTermsOfServiceUrl(),
                        swaggerApiInfo.getContactName(),
                        "",
                        ""))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 默認的SwaggerInfo
     *
     * @return 返回
     */
    @Bean
    @ConditionalOnMissingBean
    public SwaggerApiInfo swaggerApiInfo() {
        return new SwaggerApiInfo.SwaggerApiInfoBuilder()
                .title("snow")
                .description(".")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}