package com.diony.shopping.user.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台用户响应对象
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Data
@ApiModel(value = "SysUser对象", description = "后台用户响应对象")
public class SysUserRes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "账号名称")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;


}
