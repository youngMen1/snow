package com.snow.snowcore.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 請求頭
 **/
@Getter
@Setter
@ToString
@ApiModel(value = "請求頭")
public class SnowRequestHeader {

    /**
     * 客戶端id
     */
    @ApiModelProperty(value = "客戶端id")
    private String clientId;

    /**
     * 客戶端版本
     */
    @ApiModelProperty(value = "客戶端版本")
    private String clientVersion;

    /**
     * 語言
     */
    @ApiModelProperty(value = "語言")
    private String lan;

    /**
     * 渠道
     */
    @ApiModelProperty(value = "渠道")
    private String channelType;

    /**
     * 系統
     */
    @ApiModelProperty(value = "系統")
    private String systemCode;

    /**
     * token
     */
    @ApiModelProperty(value = "TOKEN")
    private String token;
}
