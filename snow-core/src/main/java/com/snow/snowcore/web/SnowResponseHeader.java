package com.snow.snowcore.web;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description snow响应
 **/
public class SnowResponseHeader {

    private String code = "0";
    private String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public SnowResponseHeader() {
    }

    public SnowResponseHeader(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "SigmaResponseHeader(code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }
}
