package com.snow.snowweb;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:13
 * @description
 **/
public class IpAddressUtil {
    public IpAddressUtil() {
    }

    public static String getUserAgentIp() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            String realIpHeader = request.getHeader("X-Real-IP");
            String xffHeader = request.getHeader("X-Forwarded-For");
            if (StringUtils.hasLength(xffHeader) && !"unKnown".equalsIgnoreCase(xffHeader)) {
                int index = xffHeader.indexOf(",");
                return index != -1 ? xffHeader.substring(0, index) : xffHeader;
            } else {
                xffHeader = realIpHeader;
                if (StringUtils.hasLength(realIpHeader) && !"unKnown".equalsIgnoreCase(realIpHeader)) {
                    return realIpHeader;
                } else {
                    if (StringUtils.isEmpty(realIpHeader) || "unknown".equalsIgnoreCase(realIpHeader)) {
                        xffHeader = request.getHeader("Proxy-Client-IP");
                    }

                    if (StringUtils.isEmpty(xffHeader) || "unknown".equalsIgnoreCase(xffHeader)) {
                        xffHeader = request.getHeader("WL-Proxy-Client-IP");
                    }

                    if (StringUtils.isEmpty(xffHeader) || "unknown".equalsIgnoreCase(xffHeader)) {
                        xffHeader = request.getHeader("HTTP_CLIENT_IP");
                    }

                    if (StringUtils.isEmpty(xffHeader) || "unknown".equalsIgnoreCase(xffHeader)) {
                        xffHeader = request.getHeader("HTTP_X_FORWARDED_FOR");
                    }

                    if (StringUtils.isEmpty(xffHeader) || "unknown".equalsIgnoreCase(xffHeader)) {
                        xffHeader = request.getRemoteAddr();
                    }

                    return xffHeader;
                }
            }
        }
    }
}
