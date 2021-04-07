package com.diony.shopping.gateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * nacos 配置参数
 *
 * @author diony_chen
 * @since 2021-03-29
 */
@Slf4j
@ConfigurationProperties(prefix="nacos", ignoreUnknownFields = true)
@Configuration
@Data
public class NacosGatewayProperties {

	private String dataId;
	
	private String groupId;

	private String address;
	
	private Long timeout;

}
