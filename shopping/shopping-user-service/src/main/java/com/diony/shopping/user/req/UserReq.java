package com.diony.shopping.user.req;

import com.diony.shopping.common.req.BaseReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserReq extends BaseReq {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String icon;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "备注")
    private String remark;
}
