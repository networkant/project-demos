package com.lqcoder.annotationdemo.controller;

import com.lqcoder.annotationdemo.annotation.OutputLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：luomengsun.
 * @Date ：Created in 15:42 2019/12/15
 * @Description：
 */
@RestController

public class TestController {
    @GetMapping("/test")
    @OutputLog
    public String testController(){
        return "测试";
    }
}
