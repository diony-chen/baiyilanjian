package com.diony.shopping.user.enums;

import lombok.Getter;

/**
 * @author diony_chen
 * @version 1.0
 * @description: 消息队列枚举配置
 * @date 2021/4/8 9:51
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_PRODUCT("release_product_exchange", "release_product_queue", "release_product_queue"),

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("shopping.order.direct", "shopping.order.cancel", "shopping.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("shopping.order.direct.ttl", "shopping.order.cancel.ttl", "shopping.order.cancel.ttl");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}