package com.snow.snowcore.web;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 響應
 **/
public class SnowResponse<T> {
    private SnowResponseHeader header;
    private T data;

    public SnowResponse(T data) {
        this.data = data;
    }

    public static <T> SnowResponse<T> ok(T data) {
        return new SnowResponse(new SnowResponseHeader(), data);
    }

    public static <T> SnowResponse<T> create(String code, String message) {
        return (SnowResponse<T>) create(code, message, (Object) null);
    }

    public static <T> SnowResponse<T> create(String code, String message, T data) {
        SnowResponse<T> response = new SnowResponse();
        response.setHeader(new SnowResponseHeader(code, message));
        response.setData(data);
        return response;
    }

    public static SnowResponseBuilder builder() {
        SnowResponseBuilder sigmaResponseBuilder = new SnowResponseBuilder(new SnowResponse());
        return sigmaResponseBuilder;
    }

    public SnowResponseHeader getHeader() {
        return this.header;
    }

    public T getData() {
        return this.data;
    }

    public void setHeader(final SnowResponseHeader header) {
        this.header = header;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public SnowResponse() {
    }

    public SnowResponse(final SnowResponseHeader header, final T data) {
        this.header = header;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SnowResponse(header=" + this.getHeader() + ", data=" + this.getData() + ")";
    }
}
