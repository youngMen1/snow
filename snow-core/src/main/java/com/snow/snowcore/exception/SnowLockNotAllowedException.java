package com.snow.snowcore.exception;

import com.snow.snowcore.web.SnowResponseHeader;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 鎖獲取不到異常
 **/
public class SnowLockNotAllowedException extends SnowException {
    public SnowLockNotAllowedException(String message, Object detail) {
        super(new SnowResponseHeader(SnowResponseHeader.DIST_LOCK_NOT_ACQUIRED, message, detail));
    }
}
