package com.snow.snowweb;

import com.snow.snowcore.exception.SnowException;
import com.snow.snowcore.web.SnowResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:12
 * @description
 **/
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    @Autowired
    MessageSource messageSource;

    public GlobalDefaultExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public SnowResponse handleException(HttpServletRequest request, Exception ex) {
        String message = MessageFormat.format("url:{0},method:{1},exception:{2}", request.getRequestURI(), request.getMethod(), ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        log.error("未知異常{}", message);
        return SnowResponse.builder().withCode("500").withMessage("Server error.").build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public SnowResponse methodArgumentNotValidHandler(HttpServletRequest request, Exception e) throws Exception {
        List<FieldError> fieldErrors = null;
        if (e instanceof BindException) {
            fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
        } else if (e instanceof MethodArgumentNotValidException) {
            fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }

        String tip = "";
        Locale currentLocale = LocaleContextHolder.getLocale();
        if (CollectionUtils.isEmpty(fieldErrors)) {
            tip = "Paramters invalid！" + e.getMessage();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator var7 = fieldErrors.iterator();

            while (var7.hasNext()) {
                FieldError fieldError = (FieldError) var7.next();
                String errorMessage = this.messageSource.getMessage(fieldError, currentLocale);
                stringBuilder.append(fieldError.getField()).append("：").append(errorMessage).append(", ");
            }

            tip = stringBuilder.toString();
        }

        return SnowResponse.create("00000001", tip);
    }

    @ExceptionHandler({SnowException.class})
    @ResponseBody
    public SnowResponse handleSigmaException(SnowException ex) {
        String msg = this.messageSource.getMessage(ex.getErrorCode(), ex.getArguments(), LocaleContextHolder.getLocale());
        return SnowResponse.builder().withCode(ex.getErrorCode()).withMessage(msg).build();
    }
}
