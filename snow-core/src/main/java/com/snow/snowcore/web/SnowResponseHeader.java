package com.snow.snowcore.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description snow响应
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "響應頭")
@ToString
public class SnowResponseHeader {

    /**
     * 成功
     */
    public final static String SUCCESS_CODE = "0";

    /**
     * 服務器異常
     */
    public final static String SERVER_ERROR = "SV000";

    /**
     * 驗證失敗
     */
    public final static String VALIDATION_ERROR = "VF000";

    /**
     * 获取锁失败
     */
    public final static String DIST_LOCK_NOT_ACQUIRED = "DL001";

    /**
     * 成功
     */
    public static final SnowResponseHeader SUCCESS = new SnowResponseHeader(SUCCESS_CODE, "", null);
    
    /**
     * 響應碼
     */
    @ApiModelProperty(value = "響應碼")
    private String code;
    /**
     * 消息
     */
    @ApiModelProperty(value = "響應消息")
    private String message;
    /**
     * 詳細
     */
    @ApiModelProperty(value = "詳細")
    private Object detail;
}
