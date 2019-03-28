package com.snow.snowcore.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 請求頭
 **/
public class SnowRequestHeader {
    private String clientId;
    private String clientVer;
    private String lang;
    private String channel;
    private String systemCode;
    private String projectCode;
    private String token;

    public SnowRequestHeader() {
    }

    @JsonIgnore
    public String getSite() {
        try {
            if (StringUtils.hasLength(this.lang)) {
                String[] arr = this.lang.split("_");
                if (arr.length == 2) {
                    return arr[1];
                }
            }
        } catch (Exception var2) {
            ;
        }

        return "";
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientVer() {
        return this.clientVer;
    }

    public String getLang() {
        return this.lang;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getSystemCode() {
        return this.systemCode;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public String getToken() {
        return this.token;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public void setClientVer(final String clientVer) {
        this.clientVer = clientVer;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public void setSystemCode(final String systemCode) {
        this.systemCode = systemCode;
    }

    public void setProjectCode(final String projectCode) {
        this.projectCode = projectCode;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String toString() {
        return "SigmaRequestHeader(clientId=" + this.getClientId() + ", clientVer=" + this.getClientVer() + ", lang=" + this.getLang() + ", channel=" + this.getChannel() + ", systemCode=" + this.getSystemCode() + ", projectCode=" + this.getProjectCode() + ", token=" + this.getToken() + ")";
    }
}
