package com.ququ.star.gateway.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Author: 彭淳
 * @Date: 2023/11/28 15:25
 */
@Component
@Slf4j
public class DynamicGatewayRouteConfig implements ApplicationEventPublisherAware {

    @Value("${route.dynamic.dataId}")
    private String dataId;

    @Value("${route.dynamic.namespace}")
    private String namespace;

    @Value("${route.dynamic.group}")
    private String group;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;
    @Value("${spring.cloud.nacos.config.username}")
    private String username;
    @Value("${spring.cloud.nacos.config.password}")
    private String password;
    private RouteDefinitionWriter routeDefinitionWriter;

    private final long timeoutMs=5000;

    @Autowired
    public void setRouteDefinitionWriter(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }
    private ApplicationEventPublisher applicationEventPublisher;

    private static final List<String> ROUTES = new ArrayList<>();

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 程序首次启动, 并加载初始化路由配
     */
    @SneakyThrows
    @PostConstruct
    public void dynamicRouteByNacosListener() {
        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);
        properties.put("namespace",namespace);
        properties.put("username",username);
        properties.put("password",password);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String initConfigInfo = configService.getConfig(dataId, group, timeoutMs);
        batchAddOrUpdateRouteAndPublish(initConfigInfo);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                batchAddOrUpdateRouteAndPublish(configInfo);
            }
        });
    }

    /**
     * 批量添加或更新路由，及发布 路由
     * @param initConfigInfo
     */
    private void batchAddOrUpdateRouteAndPublish(String initConfigInfo){
        clearRoute();
        List<RouteDefinition> routeDefinitions = JSONObject.parseArray(initConfigInfo, RouteDefinition.class);
        for (RouteDefinition routeDefinition : routeDefinitions) {
            addRoute(routeDefinition);
        }
        publish();
        log.info("添加路由信息. {}", JSON.toJSONString(routeDefinitions));
    }

    /**
     * 发布事件
     */
    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    /**
     * 新增路由
     * @param routeDefinition
     */
    private void addRoute(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        ROUTES.add(routeDefinition.getId());
    }

    /**
     * 清空路由
     */
    private void clearRoute() {
        for (String route : ROUTES) {
            this.routeDefinitionWriter.delete(Mono.just(route)).subscribe();
        }
        ROUTES.clear();
    }

}
