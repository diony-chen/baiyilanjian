package com.diony.shopping.auth.feign.service;

import com.diony.shopping.auth.domain.UserDTO;
import com.diony.shopping.common.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: TODO
 * @author diony_chen
 * @date 2021/4/6 18:13
 * @version 1.0
 */
@FeignClient(value = "shopping-user-service")
public interface UserService {

    @GetMapping("/admin/loadByUsername")
    UserDTO loadUserByUsername(@RequestParam String username);
}
