package com.snow.snowcore.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 響應
 **/
@Getter
@Setter
@NoArgsConstructor
public class SnowResponse<T> {
    /**
     * 響應頭，默認成功
     */
    @ApiModelProperty(value = "響應頭")
    private SnowResponseHeader header = SnowResponseHeader.SUCCESS;

    /**
     * 響應體
     */
    @ApiModelProperty(value = "響應數據")
    private T data;

    /**
     * 構造器
     *
     * @param data 數據
     */
    public SnowResponse(T data) {
        this.data = data;
    }

    /**
     * 創建
     *
     * @param data 數據
     * @param <T>  泛型
     * @return 響應
     */
    public static <T> SnowResponse create(T data) {
        return new SnowResponse(data);
    }
}
