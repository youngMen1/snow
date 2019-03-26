package com.snow.snowcore.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 請求，默認只支持JSON格式的.
 **/
@Getter
@Setter
@ApiModel(value = "響應")
public class SnowRequest<T> {

    /**
     * 請求頭
     */
    @ApiModelProperty(value = "請求頭")
    private SnowRequestHeader header;

    /**
     * 請求數據體
     */
    @ApiModelProperty(value = "請求數據")
    private T data;
}
