package com.snow.snowcore.exception;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Snow框架 異常類
 **/
public class SnowException extends RuntimeException {

    private String errorCode = "500";
    private Object[] arguments;

    public SnowException() {
    }

    public SnowException(String message) {
        super(message);
    }

    public SnowException(String message, Throwable cause) {
        super(message, cause);
    }

    public SnowException(Throwable cause) {
        super(cause);
    }

    protected SnowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SnowException withArguments(Object... arguments) {
        this.arguments = arguments;
        return this;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public Object[] getArguments() {
        return this.arguments;
    }
}
