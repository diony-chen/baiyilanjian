package com.diony.shopping.gateway.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.diony.shopping.gateway.config.NacosGatewayProperties;
import com.diony.shopping.gateway.service.impl.DynamicRouterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * nacos 路由配置监听
 *
 * @author diony_chen
 * @since 2021-03-29
 */
@Slf4j
@Component
public class DynamicRouterListener implements CommandLineRunner {

	@Autowired
    DynamicRouterServiceImpl dynamicRouteService;

	@Autowired
    NacosGatewayProperties nacosGatewayProperties;

    /**
     * nacos 配置监听
     */
    public void nacosConfigListener (){
        try {
            ConfigService configService = NacosFactory.createConfigService(nacosGatewayProperties.getAddress());
            configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                	List<RouteDefinition> list = JSONUtil.toList(configInfo, RouteDefinition.class);
                	list.forEach(definition->{
                		dynamicRouteService.updateDynamicRouter(definition);
                	});
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
           e.printStackTrace();
        }
    }

	@Override
	public void run(String... args) {
        nacosConfigListener();
	}
    
}