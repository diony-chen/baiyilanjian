package com.diony.shopping.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan(basePackages = "com.diony.shopping.user.mapper")
@EnableFeignClients(basePackages = {"com.diony.shopping"})
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.diony.shopping")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
