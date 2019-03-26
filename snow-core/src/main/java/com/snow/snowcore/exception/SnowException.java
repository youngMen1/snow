package com.snow.snowcore.exception;

import com.snow.snowcore.web.SnowResponseHeader;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Snow框架 異常類
 **/
public class SnowException extends RuntimeException {

    @Getter
    private SnowResponseHeader responseHeader;

    protected SnowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 通過header來構造
     *
     * @param responseHeader
     */
    public SnowException(@NotNull SnowResponseHeader responseHeader) {
        super(responseHeader.getMessage());
        this.responseHeader = responseHeader;
    }
}
