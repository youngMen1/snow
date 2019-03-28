package com.snow.snowcore.web;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/27 19:22
 * @description
 **/
public class SnowResponseBuilder {
    private SnowResponse sigmaResponse;

    public SnowResponseBuilder(SnowResponse sigmaResponse) {
        this.sigmaResponse = sigmaResponse;
    }

    public SnowResponseBuilder withCode(String code) {
        if (this.sigmaResponse.getHeader() == null) {
            this.sigmaResponse.setHeader(new SnowResponseHeader("", ""));
        }

        this.sigmaResponse.getHeader().setCode(code);
        return this;
    }

    public SnowResponseBuilder withMessage(String message) {
        if (this.sigmaResponse.getHeader() == null) {
            this.sigmaResponse.setHeader(new SnowResponseHeader("", ""));
        }

        this.sigmaResponse.getHeader().setMessage(message);
        return this;
    }

    public SnowResponseBuilder withData(Object data) {
        this.sigmaResponse.setData(data);
        return this;
    }

    public SnowResponse build() {
        return this.sigmaResponse;
    }
}
