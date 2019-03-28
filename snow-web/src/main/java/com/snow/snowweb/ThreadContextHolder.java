package com.snow.snowweb;

import com.snow.snowcore.web.SnowRequestHeader;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:14
 * @description
 **/
public class ThreadContextHolder {
    private static final ThreadLocal<Map<String, Object>> TH_CONTEXT_HOLDER = new ThreadLocal();
    private static Map<String, Class<?>> PATH_DICT = null;

    public ThreadContextHolder() {
    }

    public static Map<String, Class<?>> getPathDict() {
        return PATH_DICT;
    }

    public static void registerPath(String path, Class classType) {
        PATH_DICT.put(path, classType);
    }

    public static <T> T getValue(String key, Class<T> classType) {
        Map<String, Object> map = (Map) TH_CONTEXT_HOLDER.get();
        if (map == null) {
            map = new HashMap(16);
            TH_CONTEXT_HOLDER.set(map);
        }

        return castObjectToT(((Map) map).get(key), classType);
    }

    public static void setValue(String key, Object value) {
        Map<String, Object> map = (Map) TH_CONTEXT_HOLDER.get();
        if (map == null) {
            map = new HashMap(16);
            TH_CONTEXT_HOLDER.set(map);
        }

        ((Map) map).put(key, value);
    }

    public static void setValueMap(String key, Map value, Class<?> beanClass) {
        try {
            Object obj = mapToObject(value, beanClass);
            if (obj != null) {
                setValue(key, obj);
            }
        } catch (InstantiationException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static <T> T castObjectToT(Object o, Class<T> t) {
        if (t != null) {
            return o == null ? null : (T) o;
        } else {
            return null;
        }
    }

    private static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        } else {
            Object obj = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            PropertyDescriptor[] var5 = propertyDescriptors;
            int var6 = propertyDescriptors.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor property = var5[var7];
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(obj, map.get(property.getName()));
                }
            }

            return obj;
        }
    }

    public static SnowRequestHeader getHeader() {
        return (SnowRequestHeader) getValue("header", SnowRequestHeader.class);
    }

    static {
        PATH_DICT = new HashMap();
        registerPath("header", SnowRequestHeader.class);
    }
}
