package com.ququ.star.common.constent;


/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 系统常量
 */
public interface SysConstant {
    /**
     * 权限<->url对应的KEY
     */
    String OAUTH_URLS="oauth2:oauth_urls";

    /**
     * JWT令牌黑名单的KEY
     */
    String JTI_KEY_PREFIX="oauth2:black:";

    /**
     * 角色前缀
     */
    String ROLE_PREFIX="ROLE_";

    String METHOD_SUFFIX=":";

    String ROLE_ROOT_CODE="ROLE_ROOT";

}