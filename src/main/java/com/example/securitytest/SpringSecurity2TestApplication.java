package com.example.securitytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.securitytest.mapper")
public class SpringSecurity2TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity2TestApplication.class, args);
        System.out.println("----run-----");
    }

}
