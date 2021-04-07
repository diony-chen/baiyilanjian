package com.diony.shopping.auth.controller;

import com.diony.shopping.common.res.BaseRes;
import com.diony.shopping.common.res.Oauth2TokenRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public BaseRes<Oauth2TokenRes> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenRes oauth2TokenDto = Oauth2TokenRes.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();

        return BaseRes.success(oauth2TokenDto);
    }
}
