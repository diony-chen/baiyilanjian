package com.diony.shopping.user.service;

import com.diony.shopping.common.domain.User;

/**
 * @author ThinkPad
 * @version 1.0
 * @description: TODO
 * @date 2021/4/1 11:46
 */
public interface UserService {
    User queryUser(String username);
}
