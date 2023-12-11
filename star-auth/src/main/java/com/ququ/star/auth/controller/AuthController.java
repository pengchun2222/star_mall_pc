package com.ququ.star.auth.controller;

import com.ququ.star.common.constent.SysConstant;
import com.ququ.star.common.model.CommonResult;
import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.utils.OauthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 彭淳
 * @Date: 2023/11/22 11:33
 */
@RestController
@RequestMapping("oauth")
@Slf4j
public class AuthController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/logout")
    public CommonResult logout() {
        LoginVal loginVal = OauthUtils.getCurrentUser();
        log.info("令牌唯一ID：{},过期时间：{}", loginVal.getJti(), loginVal.getExpireIn());
        // 存入黑名单设置过期时间
        stringRedisTemplate.opsForValue().set(SysConstant.JTI_KEY_PREFIX + loginVal.getJti(), "", loginVal.getExpireIn(), TimeUnit.SECONDS);
        return CommonResult.success(null, "注销成功");
    }
}
