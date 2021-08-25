package com.diony.shopping.user.service.impl;

import com.diony.shopping.user.service.QueueMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class QueueMessageServiceImpl implements QueueMessageService {
 
    //由于配置类RabbitMqConfig配置类中的rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE
    private RabbitTemplate rabbitTemplate;
 
    @Autowired
    public QueueMessageServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        //设置回调为当前类对象
        rabbitTemplate.setConfirmCallback(this);
    }
 
    @Override
    public void send(Object message, String exchange, String queueRoutingKey) throws Exception {
        //构建回调id为uuid
        String callBackId = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(callBackId);
        log.info("开始发送消息内容:{}",message.toString());
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchange, queueRoutingKey, message, correlationId);
        log.info("发送定制的回调id:{}",callBackId);
    }
 
    /**
     * 消息回调确认方法
     * @param correlationData 请求数据对象
     * @param ack 是否发送成功
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        log.info(" 回调id:" + correlationData.getId());
        if (ack) {
            log.info("消息发送成功");
        } else {
            log.info("消息发送失败:" + s);
        }
    }
}