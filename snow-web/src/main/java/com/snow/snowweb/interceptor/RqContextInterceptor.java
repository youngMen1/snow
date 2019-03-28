package com.snow.snowweb.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.snowcore.web.SnowRequestHeader;
import com.snow.snowweb.LocaleMessage;
import com.snow.snowweb.ThreadContextHolder;
import com.snow.snowweb.filter.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:10
 * @description
 **/
public class RqContextInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(RqContextInterceptor.class);

    public RqContextInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request instanceof RequestWrapper) {
            RequestWrapper requestWrapper = (RequestWrapper) request;
            String bodyString = RequestWrapper.getBodyString(requestWrapper);
            ObjectMapper mapper = new ObjectMapper();
            Map m = (Map) mapper.readValue(bodyString, Map.class);
            if (m != null) {
                ThreadContextHolder.getPathDict().entrySet().forEach((stringClassEntry) -> {
                    String[] casArray = ((String) stringClassEntry.getKey()).split("\\.");
                    Map currentMap = m;
                    String currentName = (String) stringClassEntry.getKey();
                    Object currentValue = null;

                    for (int i = 0; i < casArray.length; ++i) {
                        currentName = casArray[i];
                        currentMap = (Map) m.get(currentName);
                        if (currentMap == null) {
                            break;
                        }

                        currentValue = currentMap.get(currentName);
                    }

                    if (currentMap != null) {
                        ThreadContextHolder.setValueMap(currentName, currentMap, (Class) stringClassEntry.getValue());
                    } else {
                        ThreadContextHolder.setValue(currentName, currentValue);
                    }

                });
                SnowRequestHeader header = ThreadContextHolder.getHeader();
                if (header != null) {
                    LocaleMessage.setLocale(header.getLang());
                }
            }
        }
        return true;
    }
}
