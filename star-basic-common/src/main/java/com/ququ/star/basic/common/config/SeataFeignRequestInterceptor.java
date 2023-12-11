package com.ququ.star.basic.common.config;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 彭淳
 * @Date: 2023/12/11 10:34
 * seata传递xid
 */
@Configuration
public class SeataFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //新获取一个XID
        String xid = RootContext.getXID();
        //非空验证
        if (StrUtil.isNotEmpty(xid)) {
            //移除头中原有的KID
            requestTemplate.removeHeader(RootContext.KEY_XID);
            //装配新的XID
            requestTemplate.header(RootContext.KEY_XID, xid);
        }
    }
}
