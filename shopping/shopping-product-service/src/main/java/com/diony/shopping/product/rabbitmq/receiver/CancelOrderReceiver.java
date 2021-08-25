package com.diony.shopping.user.rabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 取消订单消息的处理者
 * @author diony_chen
 * @date 2021/4/8 10:29
 * @version 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "shopping.order.cancel")
public class CancelOrderReceiver {

    @RabbitHandler
    public void handle(Long orderId){
        log.info("receive delay message orderId:{}",orderId);
    }
}