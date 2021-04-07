package com.diony.shopping.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 后台用户管理
 * @author diony_chen
 * @date 2021/4/2 15:37
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @GetMapping(value = "query")
    @ResponseBody
    public String loadByUsername(String username) {
        return "test";
    }
}
