package com.ququ.star.common.model;

import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/21 11:01
 */
@Data
public class JwtInformation {
    /**
     * JWT令牌唯一ID
     */
    private String jti;

    /**
     * 过期时间，单位秒，距离过期时间还有多少秒
     */
    private Long expireIn;
}
