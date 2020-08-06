package com.baiyilanjian.cloud.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author diony_chen
 * @create 20200806 10:21
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigCommonProperties {

    private MqProperties mq = new MqProperties();
}
