package com.snow.snowcore;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/27 19:37
 * @description
 **/
@FunctionalInterface
public interface PrintLog {
    void print(String message, String detail);
}