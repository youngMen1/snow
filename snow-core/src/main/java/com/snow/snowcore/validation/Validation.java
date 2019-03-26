package com.snow.snowcore.validation;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 驗證接口
 **/
public interface Validation<T> {

    /**
     * 執行驗證
     *
     * @param context
     * @return
     */
    ValidationResult validate(T context);
}
