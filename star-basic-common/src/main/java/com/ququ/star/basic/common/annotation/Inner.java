package com.ququ.star.basic.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: 彭淳
 * @Date: 2023/11/24 16:27
 * 服务鉴权注解
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {
    /**
     * 是否AOP统一处理
     * @return false, true
     */
    boolean value() default true;
}
