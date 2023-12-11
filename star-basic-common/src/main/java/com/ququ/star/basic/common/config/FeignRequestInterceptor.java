package com.ququ.star.basic.common.config;

import com.ququ.star.common.model.TokenConstant;
import com.ququ.star.common.utils.RequestContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: 彭淳
 * @Date: 2023/12/1 15:51
 * 用于实现令牌信息中继
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = RequestContextUtils.getRequest();
        //////获取RequestContextHolder中的信息
        //Map<String, String> headers = getHeaders(httpServletRequest);
        ////放入feign的RequestTemplate中
        //for (Map.Entry<String, String> entry : headers.entrySet()) {
        //    requestTemplate.header(entry.getKey(), entry.getValue());
        //}

        String jwtToken = httpServletRequest.getHeader(TokenConstant.TOKEN_NAME);
        String authorization = httpServletRequest.getHeader(TokenConstant.AUTHORITIES_NAME);
        requestTemplate.header(TokenConstant.TOKEN_NAME,jwtToken);
        requestTemplate.header(TokenConstant.AUTHORITIES_NAME,authorization);
    }
    /**
     * 获取原请求头
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
