package com.ququ.star.basic.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: 彭淳
 * @Date: 2023/12/1 14:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.cloud.openfeign.EnableFeignClients
public @interface EnableFeignClients {
    String[] value() default {};

    String[] basePackages() default {"com.ququ.star"};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
