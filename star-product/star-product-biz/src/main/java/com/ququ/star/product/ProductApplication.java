package com.ququ.star.product;

//import com.ququ.star.basic.common.annotation.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 彭淳
 * @Date: 2023/12/1 11:30
 */
@EnableFeignClients(basePackages = "com.ququ")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
