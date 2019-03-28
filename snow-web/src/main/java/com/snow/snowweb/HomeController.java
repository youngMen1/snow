package com.snow.snowweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:12
 * @description
 **/
@Controller
public class HomeController {
    public HomeController() {
    }

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }
}
