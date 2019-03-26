package com.snow.snowcore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description
 **/
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
