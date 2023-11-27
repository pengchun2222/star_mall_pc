package com.ququ.star.basic.common.handler;

import com.ququ.star.basic.common.annotation.Inner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * @Author: 彭淳
 * @Date: 2023/11/24 15:46
 */
@Component
@Slf4j
public class SecurityHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        //Set<RequestMappingInfo> requestMappingInfos = handlerMethods.keySet();
        //for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
        //    RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
        //    for (RequestMethod method : methodsCondition.getMethods()) {
        //
        //    }
        //    HandlerMethod handlerMethod = handlerMethods.get(requestMappingInfo);
        //    Inner annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
        //    log.info("inner:{}",annotation);
        //}
        return true;
    }
}
