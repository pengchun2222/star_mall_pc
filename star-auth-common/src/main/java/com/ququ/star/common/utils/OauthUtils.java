package com.ququ.star.common.utils;


import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.model.RequestConstant;

public class OauthUtils {

    /**
     * 获取当前请求登录用户的详细信息
     */
    public static LoginVal getCurrentUser(){
        return (LoginVal) RequestContextUtils.getRequest().getAttribute(RequestConstant.LOGIN_VAL_ATTRIBUTE);
    }
}