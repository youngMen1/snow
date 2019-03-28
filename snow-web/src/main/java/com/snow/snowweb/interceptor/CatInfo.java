package com.snow.snowweb.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.snow.snowcore.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:06
 * @description
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatInfo {
    private static final Logger log = LoggerFactory.getLogger(CatInfo.class);
    private static ThreadLocal<CatInfo> catInfoThreadLocal = new ThreadLocal();
    private String className;
    private String methodName;
    private Object[] args;
    private Object response;
    private ExecutedTimeMetrics controllerMetric = new ExecutedTimeMetrics();
    private List<ExecutedTimeMetrics> apiRequestMetrics = Lists.newArrayList();
    private Exception exception;

    public CatInfo() {
    }

    static void init(String className, String methodName) {
        CatInfo catInfo = new CatInfo();
        catInfo.setClassName(className);
        catInfo.setMethodName(methodName);
        catInfoThreadLocal.set(catInfo);
    }

    static void logAndRemove() {
        CatInfo info = get();
        String message = String.format("Time cost: %d %s->%s", info.getControllerMetric().getDuration(), info.getClassName(), info.getMethodName());

        try {
            log.info(message.concat("\n" + JsonUtils.serialize(catInfoThreadLocal.get(), true)));
        } catch (IOException var3) {
            var3.printStackTrace();
            log.error("logAndRemove序列化catInfo出錯", var3);
        }

        catInfoThreadLocal.remove();
    }

    public static void addApiRequestMetrics(ExecutedTimeMetrics executedTimeMetrics) {
        get().getApiRequestMetrics().add(executedTimeMetrics);
    }

    public static void addControllerExecutedTime(ExecutedTimeMetrics executedTimeMetrics) {
        get().setControllerMetric(executedTimeMetrics);
    }

    public static void addArgs(Object[] args) {
        get().setArgs(args);
    }

    public static void addResponse(Object response) {
        get().setResponse(response);
    }

    public static void addException(Exception exception) {
        get().setException(exception);
    }

    private static CatInfo get() {
        if (catInfoThreadLocal.get() == null) {
            catInfoThreadLocal.set(new CatInfo());
        }

        return (CatInfo) catInfoThreadLocal.get();
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public Object getResponse() {
        return this.response;
    }

    public ExecutedTimeMetrics getControllerMetric() {
        return this.controllerMetric;
    }

    public List<ExecutedTimeMetrics> getApiRequestMetrics() {
        return this.apiRequestMetrics;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public void setArgs(final Object[] args) {
        this.args = args;
    }

    public void setResponse(final Object response) {
        this.response = response;
    }

    public void setControllerMetric(final ExecutedTimeMetrics controllerMetric) {
        this.controllerMetric = controllerMetric;
    }

    public void setApiRequestMetrics(final List<ExecutedTimeMetrics> apiRequestMetrics) {
        this.apiRequestMetrics = apiRequestMetrics;
    }

    public void setException(final Exception exception) {
        this.exception = exception;
    }
}
