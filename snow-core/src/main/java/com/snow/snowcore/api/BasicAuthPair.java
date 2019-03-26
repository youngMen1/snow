package com.snow.snowcore.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicAuthPair {
    /**
     * 用戶名
     */
    private String userName;

    /**
     * 密碼
     */
    private String password;
}
