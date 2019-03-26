package com.snow.snowcore.web;

import com.snow.snowcore.exception.SnowException;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 全局異常處理
 **/
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 未知異常處理
     *
     * @param request 請求
     * @param ex      異常
     * @return 返回
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SnowResponse handleException(HttpServletRequest request, Exception ex) {
        var message = MessageFormat.format("url:{0},method:{1},exception:{2}",
                request.getRequestURI(),
                request.getMethod(),
                ex.getMessage() + ex.getStackTrace());

        log.error("未知異常{}", message);

        var response = new SnowResponse<>();
        response.setHeader(
                new SnowResponseHeader(
                        SnowResponseHeader.SERVER_ERROR,
                        "服務器異常！", message));

        return response;
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param ex 驗證異常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public SnowResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var response = new SnowResponse<>();

        var detail = ex.getBindingResult().getAllErrors().get(0);
        response.setHeader(
                new SnowResponseHeader(
                        SnowResponseHeader.VALIDATION_ERROR,
                        "驗證失敗！",
                        detail));

        log.debug("驗證失敗！{}", detail);

        return response;
    }

    /**
     * 處理Sigma 框架異常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(SnowException.class)
    @ResponseBody
    public SnowResponse handleSigmaException(SnowException ex) {

        var response = new SnowResponse<>();
        response.setHeader(ex.getResponseHeader());

        log.debug("基礎異常！{}", ex.getResponseHeader().toString());

        return response;
    }
}
