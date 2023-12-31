package com.ququ.star.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ququ.star.common.model.CommonResult;
import com.ququ.star.common.model.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 20:51
 * 网关全局统一异常处理
 * @Order(-1)：优先级一定要比ResponseStatusExceptionHandler低
 */
@RequiredArgsConstructor
@Component
@Order(-1)
@Slf4j
public class GlobalErrorExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if(response.isCommitted()) {
            return Mono.error(ex);
        }
        CommonResult resultMsg=CommonResult.failed(ResultCode.UNAUTHORIZED);

        // JOSN格式返回
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        //处理TOKEN失效的异常
        if (ex instanceof InvalidTokenException){
            resultMsg=CommonResult.failed(ResultCode.INVALID_TOKEN);
        }

        CommonResult finalResultMsg = resultMsg;
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                //todo 返回响应结果，根据业务需求，自己定制
                return bufferFactory.wrap(new ObjectMapper().writeValueAsBytes(finalResultMsg));
            }
            catch (Exception e) {
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));

    }
}
