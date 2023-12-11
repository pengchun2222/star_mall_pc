package com.ququ.star.basic.common.exception;

import cn.hutool.json.JSONUtil;
import com.ququ.star.common.model.CommonResult;
import com.ququ.star.common.model.ResultCode;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class UserErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = null;
        try {
            // 获取原始的返回内容
            String json = Util.toString(response.body().asReader());
            exception = new RuntimeException(json);
            // 将返回内容反序列化为Result，这里应根据自身项目作修改
            CommonResult result = JSONUtil.toBean(json, CommonResult.class);
            // 业务异常抛出简单的 RunTimeException，保留原来错误信息
            if(result.getCode().equals(ResultCode.FAILED.getCode())) {
                exception = new RuntimeException(result.getMessage());
            }

        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return exception;
    }
}