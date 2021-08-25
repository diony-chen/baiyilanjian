package com.diony.shopping.user.rabbitmq.send;

import com.diony.shopping.user.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author diony_chen
 * @version 1.0
 * @description: 取消订单消息的发出者
 * @date 2021/4/8 10:24
 */
@Slf4j
@Component
public class CancelOrderSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(String orderId, final long delayTimes) {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_PRODUCT.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
        log.info("send delay message orderId:{}", orderId);
    }
}