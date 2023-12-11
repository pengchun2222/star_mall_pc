package com.ququ.star.gateway.config.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ququ.star.common.constent.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 彭淳
 * @Date: 2023/11/21 10:47
 * 鉴权管理器 用于认证成功之后对用户的权限进行鉴权
 */
@Component
@Slf4j
public class JwtAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        // 获取访问路径
        URI uri = authorizationContext.getExchange().getRequest().getURI();

        // 获取请求方式
        String methodValue = authorizationContext.getExchange().getRequest().getMethodValue();

        /**
         * 适配路径
         */
        String resultFulPath = methodValue + SysConstant.METHOD_SUFFIX + uri.getPath();

        // 获取所有uri->角色关系
        Map<String,List<String>> entries = redisTemplate.opsForHash().entries(SysConstant.OAUTH_URLS);
        List<String> authorities = new ArrayList<>();

        entries.forEach((url, roles) -> {
            if(antPathMatcher.match(url,resultFulPath)) {
                authorities.addAll(roles);
            }
        });

        //List<String> authorities = (List<String>) redisTemplate.opsForHash().get(SysConstant.OAUTH_URLS, resultFulPath);

        // 认证通过且角色匹配的用户可访问当前路径
        return mono
                //判断是否认证成功
                .filter(Authentication::isAuthenticated)
                //获取认证后的全部权限
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                //如果权限包含则判断为true
                .any(authority -> {
                    //超级管理员直接放行
                    if (StrUtil.equals(SysConstant.ROLE_ROOT_CODE, authority)) {
                        return true;
                    }
                    //其他必须要判断角色是否存在交集
                    log.info("authorities:{}",authorities);
                    log.info("authority:{}",authority);
                    return CollectionUtil.isNotEmpty(authorities) && authorities.contains(authority);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));

    }
}
