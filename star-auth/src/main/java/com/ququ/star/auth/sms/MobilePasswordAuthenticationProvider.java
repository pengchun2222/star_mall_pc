package com.ququ.star.auth.sms;

import com.ququ.star.auth.sms.service.SmsCodeUserDetailService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 彭淳
 * @Date: 2023/11/24 10:38
 */
public class MobilePasswordAuthenticationProvider implements AuthenticationProvider {


    private final SmsCodeUserDetailService userDetailService;

    private final PasswordEncoder passwordEncoder;

    public MobilePasswordAuthenticationProvider(SmsCodeUserDetailService userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobilePasswordAuthenticationToken authenticationToken = (MobilePasswordAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();
        //查询数据库，加载用户详细信息
        UserDetails user = userDetailService.loadUserByMobile(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("手机号或密码错误");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("手机号或密码错误");
        }
        MobilePasswordAuthenticationToken authenticationResult = new MobilePasswordAuthenticationToken(user, password, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MobilePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
