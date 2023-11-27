package com.ququ.star.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 彭淳
 * @Date: 2023/11/21 11:33
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.ququ.star.*"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
