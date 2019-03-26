package com.snow.snowcore.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Swagger 配置
 **/
@Getter
@Builder
@ToString(callSuper = true)
public class SwaggerApiInfo {
    /**
     * 標題
     */
    private String title;

    /**
     * 版本
     */
    private String version;

    /**
     * 描述
     */
    private String description;

    /**
     * 服務Url
     */
    private String termsOfServiceUrl;

    /**
     * 聯繫人 姓名
     */
    private String contactName;

    /**
     * 聯繫人地址
     */
    private String contactUrl;

    /**
     * 聯繫人郵箱
     */
    private String contactEmail;
}
