package com.snow.snowcore.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

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

    private SnowRequestHeader header;
    @Valid
    private T data;
    private Boolean mockData;

    public SnowRequestHeader getHeader() {
        return this.header;
    }

    public T getData() {
        return this.data;
    }

    public Boolean getMockData() {
        return this.mockData;
    }

    public void setHeader(final SnowRequestHeader header) {
        this.header = header;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMockData(final Boolean mockData) {
        this.mockData = mockData;
    }

    public SnowRequest() {
    }

    public SnowRequest(final SnowRequestHeader header, final T data, final Boolean mockData) {
        this.header = header;
        this.data = data;
        this.mockData = mockData;
    }

    public String toString() {
        return "SnowRequest(header=" + this.getHeader() + ", data=" + this.getData() + ", mockData=" + this.getMockData() + ")";
    }
}
