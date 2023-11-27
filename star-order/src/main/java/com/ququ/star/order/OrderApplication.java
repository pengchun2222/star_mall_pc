package com.ququ.star.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 彭淳
 * @Date: 2023/11/22 15:00
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.ququ.star.*"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
