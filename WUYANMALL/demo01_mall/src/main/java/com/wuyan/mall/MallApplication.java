package com.wuyan.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@SpringBootApplication
@MapperScan(basePackages = "com.wuyan.mall.mapper")
public class MallApplication {

    public static void main(String[] args) {


        SpringApplication.run(MallApplication.class, args);
    }

}
