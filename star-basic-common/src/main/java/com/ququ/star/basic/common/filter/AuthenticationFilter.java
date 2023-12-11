package com.ququ.star.basic.common.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ququ.star.common.config.SysParameterConfig;
import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.model.RequestConstant;
import com.ququ.star.common.model.TokenConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 公众号：码猿技术专栏
 * 微服务过滤器，解密网关传递的用户信息，将其放入request中，便于后期业务方法直接获取用户信息
 */
@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private SysParameterConfig sysParameterConfig;

    /**
     * 具体方法主要分为两步
     * 1. 解密网关传递的信息
     * 2. 将解密之后的信息封装放入到request中
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        List<String> ignoreUrls = sysParameterConfig.getIgnoreUrls().stream()
                .map(ignoreUrl -> ignoreUrl.substring(ignoreUrl.indexOf("/", 2)))
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(ignoreUrls)) {
            if (ignoreUrls.contains(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        //获取请求头中的加密的用户信息
        String token = request.getHeader(TokenConstant.TOKEN_NAME);
        if (StrUtil.isNotBlank(token)) {
            //解密
            String json = Base64.decodeStr(token);
            JSONObject jsonObject = JSON.parseObject(json);
            //获取用户身份信息、权限信息
            String principal = jsonObject.getString(TokenConstant.PRINCIPAL_NAME);
            String userId = jsonObject.getString(TokenConstant.USER_ID);
            String jti = jsonObject.getString(TokenConstant.JTI);
            Long expireIn = jsonObject.getLong(TokenConstant.EXPR);
            JSONArray tempJsonArray = jsonObject.getJSONArray(TokenConstant.AUTHORITIES_NAME);
            //权限
            String[] authorities = (String[]) tempJsonArray.toArray(new String[0]);
            //放入LoginVal
            LoginVal loginVal = new LoginVal();
            loginVal.setUserId(userId);
            loginVal.setUsername(principal);
            loginVal.setAuthorities(authorities);
            loginVal.setJti(jti);
            loginVal.setExpireIn(expireIn);
            //放入request的attribute中
            request.setAttribute(RequestConstant.LOGIN_VAL_ATTRIBUTE, loginVal);
            filterChain.doFilter(request, response);
            return;
        }
        throw new RuntimeException("请从网关进入");
    }
}