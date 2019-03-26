package com.snow.snowcore.validation;

import lombok.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 驗證結果
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 錯誤代碼
     */
    private String code;

    /**
     * 消息
     */
    private String message;
}
