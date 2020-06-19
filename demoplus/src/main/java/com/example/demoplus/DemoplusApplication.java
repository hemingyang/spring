package com.example.demoplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demoplus.mapper")
public class DemoplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoplusApplication.class, args);
    }

}
