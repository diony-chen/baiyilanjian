package com.baiyilanjian.cloud.common.properties;

import lombok.Data;

/**
 * 发送邮件交换机
 * @author diony_chen
 * @create 20200806 10:21
 */
@Data
public class ExchangeProperties {

    private String executeSql;
    private String runJob;

    private String sendMail;
}
