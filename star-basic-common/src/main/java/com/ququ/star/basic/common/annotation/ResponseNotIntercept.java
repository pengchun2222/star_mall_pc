package com.ququ.star.basic.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: 彭淳
 * @Date: 2023/12/1 10:29
 * 返回放行注解
 * 在类或者方法上使用则不会在ResponseResult类中进一步封装返回值，直接返回生态值
 */
@Target({ElementType.METHOD, ElementType.TYPE})  //可以在字段、方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseNotIntercept {
    String value() default "";
}
