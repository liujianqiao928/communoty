package com.custchina.shequdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.custchina.shequdemo.mapper")
public class ShequdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShequdemoApplication.class, args);
    }

}
