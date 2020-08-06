package com.baiyilanjian.cloud.common.properties;

import lombok.Data;

/**
 * @author diony_chen
 * @create 20200806 10:21
 */
@Data
public class QueueProperties {

    private String executeSql;
    private String runJobTest;


    private String sendMail;
    private String sendMailDlx;
    private String sendMailTrt;
}
