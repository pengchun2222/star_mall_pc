package com.ququ.star.auth.config;

import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

public class JwtEnhanceAccessTokenConverter extends DefaultAccessTokenConverter {
    public JwtEnhanceAccessTokenConverter(){
        super.setUserTokenConverter(new JwtEnhanceUserAuthenticationConverter());
    }
}