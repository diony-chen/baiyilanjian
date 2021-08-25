package com.diony.shopping.user.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public interface QueueMessageService extends RabbitTemplate.ConfirmCallback {
    /**
     * 发送消息到rabbitmq消息队列
     * @param message 消息内容
     * @param exchange 交换配置
     * @param queueRoutingKey routingKey的队列
     * @throws Exception
     */
    void send(Object message, String exchange, String queueRoutingKey) throws Exception;
}