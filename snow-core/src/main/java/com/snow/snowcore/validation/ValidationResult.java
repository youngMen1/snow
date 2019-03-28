package com.snow.snowcore.validation;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 驗證結果
 **/
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

    public Boolean getSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidationResult(success=" + this.getSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }

    public ValidationResult() {
    }

    public ValidationResult(final Boolean success, final String code, final String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
