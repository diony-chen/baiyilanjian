package com.diony.shopping.common.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: Oauth2获取Token返回信息封装
 * @author diony_chen
 * @date 2021/4/1 15:09
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenRes {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
