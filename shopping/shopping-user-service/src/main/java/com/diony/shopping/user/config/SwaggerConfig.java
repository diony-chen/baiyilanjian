package com.diony.shopping.user.config;

import com.diony.shopping.common.config.BaseSwaggerConfig;
import com.diony.shopping.common.domain.Swagger;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author diony_chen
 * @version 1.0
 * @description: Swagger API文档相关配置
 * @date 2021/4/1 16:03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public Swagger swagger() {
        return Swagger.builder()
                .apiBasePackage("com.diony.shopping.auth.controller")
                .title("shopping认证中心")
                .description("shopping认证中心相关接口文档")
                .contactName("diony")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
