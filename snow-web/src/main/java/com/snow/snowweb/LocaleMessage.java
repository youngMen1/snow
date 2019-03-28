package com.snow.snowweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:13
 * @description
 **/
@Component
public class LocaleMessage {
    @Autowired
    private MessageSource messageSource;

    public LocaleMessage() {
    }

    public static void setLocale(String lang) {
        if (StringUtils.hasLength(lang)) {
            Locale locale = LocaleContextHolder.getLocale();
            byte var3 = -1;
            switch (lang.hashCode()) {
                case 96647257:
                    if (lang.equals("en_hk")) {
                        var3 = 1;
                    }
                    break;
                case 96647594:
                    if (lang.equals("en_sg")) {
                        var3 = 2;
                    }
                    break;
                case 115862452:
                    if (lang.equals("zh_hk")) {
                        var3 = 0;
                    }
            }

            switch (var3) {
                case 0:
                    locale = Locale.TRADITIONAL_CHINESE;
                    break;
                case 1:
                case 2:
                    locale = Locale.US;
            }

            LocaleContextHolder.setLocale(locale);
        }

    }

    public String getMessage(String code) {
        return this.getMessage(code, new Object[0]);
    }

    public String getMessage(String code, String defaultMessage) {
        return this.getMessage(code, (Object[]) null, (String) defaultMessage);
    }

    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.getMessage(code, (Object[]) null, defaultMessage, locale);
    }

    public String getMessage(String code, Locale locale) {
        return this.getMessage(code, (Object[]) null, "", locale);
    }

    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, "", locale);
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }
}