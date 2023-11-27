package com.ququ.star.auth.sms.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 11:02
 */
public interface SmsCodeUserDetailService {

    /**
     * 根据手机号查询用户信息
     * @param mobile  手机号码
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;
}
