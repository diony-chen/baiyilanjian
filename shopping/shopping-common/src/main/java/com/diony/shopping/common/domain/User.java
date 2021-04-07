package com.diony.shopping.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户信息
 * Created by macro on 2020/6/19.
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

    public User() {
    }

    public User(List<String> roles) {
        this.roles = roles;
    }

    public User(Long id, String username, String password, Integer status, String clientId, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.clientId = clientId;
        this.roles = roles;
    }
}
