package com.diony.shopping.gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * nacos 动态路由管理
 *
 * @author diony_chen
 * @since 2021-03-29
 */
@Slf4j
@Service
public class DynamicRouterServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    RouteDefinitionWriter routeDefinitionWriter;

    ApplicationEventPublisher publisher;


    /**
     * 添加动态路由
     * @param definition
     * @return
     */
    public String addDynamicRouter(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }


    /**
     * 更新动态路由
     * @param definition
     * @return
     */
    public String updateDynamicRouter(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: "+definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }


    }

    /**
     * 删除动态路由
     * @param id
     * @return
     */
    public String deleteDynamicRouter(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

}
