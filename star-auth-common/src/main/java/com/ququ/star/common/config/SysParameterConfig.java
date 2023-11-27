package com.ququ.star.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/21 11:00
 * 白名单
 */
@ConfigurationProperties(prefix = "oauth2.cloud.sys.parameter")
@Configuration
@Data
public class SysParameterConfig {
    /**
     * 白名单
     */
    private List<String> ignoreUrls;
}