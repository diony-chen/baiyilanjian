package com.diony.shopping.user.req;

import com.diony.shopping.common.req.BaseReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginReq extends BaseReq {

    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
