package com.ququ.star.basic.common.exception;

import com.alibaba.fastjson.JSON;
import com.ququ.star.basic.common.annotation.ResponseNotIntercept;
import com.ququ.star.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Author: 彭淳
 * @Date: 2023/11/24 14:07
 */
@Slf4j
@RestControllerAdvice
public class GlobalHandler  {

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handle(ApiException e) {
        return CommonResult.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public CommonResult errorHandler(Exception exception) {
        exception.printStackTrace();
        // 拦截seata异常抛出，取出真实抛出异常。
        if(exception.getMessage().equals("try to proceed invocation error")){
            return CommonResult.failed(exception.getCause().getMessage());
        }
        return CommonResult.failed(exception.getMessage());
    }


    //@Override
    //public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    //    if (returnType.getDeclaringClass().isAnnotationPresent(ResponseNotIntercept.class)) {
    //        //若在类中加了@ResponseNotIntercept 则该类中的方法不用做统一的拦截
    //        return false;
    //    }
    //    if (returnType.getMethod().isAnnotationPresent(ResponseNotIntercept.class)) {
    //        //若方法上加了@ResponseNotIntercept 则该方法不用做统一的拦截
    //        return false;
    //    }
    //    return true;
    //}
    //
    //@Override
    //public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
    //                              Class<? extends HttpMessageConverter<?>> selectedConverterType,
    //                              ServerHttpRequest request, ServerHttpResponse response) {
    //    if (body instanceof CommonResult) {
    //        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
    //        return body;
    //    }
    //    if (body instanceof String) {
    //        //解决返回值为字符串时，不能正常包装
    //        return JSON.toJSONString(CommonResult.success(body));
    //    }
    //    return body;
    //    //return CommonResult.success(body);
    //}
}
