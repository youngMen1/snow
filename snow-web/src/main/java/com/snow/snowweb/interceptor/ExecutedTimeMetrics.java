package com.snow.snowweb.interceptor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:09
 * @description
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutedTimeMetrics {
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    )
    private LocalDateTime startDateTime;
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    )
    private LocalDateTime endDateTime;
    private String apiName;
    private Exception exception;

    public ExecutedTimeMetrics(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Long getDuration() {
        return this.startDateTime != null && this.endDateTime != null ? Duration.between(this.startDateTime, this.endDateTime).toMillis() : -1L;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public String getApiName() {
        return this.apiName;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setStartDateTime(final LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(final LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setApiName(final String apiName) {
        this.apiName = apiName;
    }

    public void setException(final Exception exception) {
        this.exception = exception;
    }

    public String toString() {
        return "ExecutedTimeMetrics(startDateTime=" + this.getStartDateTime() + ", endDateTime=" + this.getEndDateTime() + ", apiName=" + this.getApiName() + ", exception=" + this.getException() + ")";
    }

    public ExecutedTimeMetrics() {
    }

    public ExecutedTimeMetrics(final LocalDateTime startDateTime, final LocalDateTime endDateTime, final String apiName, final Exception exception) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.apiName = apiName;
        this.exception = exception;
    }
}
