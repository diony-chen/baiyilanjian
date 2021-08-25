package com.diony.shopping.user.controller;

import com.diony.shopping.common.domain.User;
import com.diony.shopping.common.res.BaseRes;
import com.diony.shopping.user.rabbitmq.send.CancelOrderSender;
import com.diony.shopping.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "后台用户管理")
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CancelOrderSender cancelOrderSender;

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping(value = "loadByUsername")
    @ResponseBody
    public User loadByUsername(String username) {
        return userService.queryUser(username);
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping(value = "cancelOrder")
    @ResponseBody
    public BaseRes cancelOrder(String orderId) {
        cancelOrderSender.sendMessage(orderId, 1);
        return BaseRes.success(null);
    }
}
