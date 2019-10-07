package com.wuyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD:WUYANMALL/demo01_mall/src/main/java/com/wuyan/MallApplication.java
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.wuyan.mall.mapper")
@EnableAspectJAutoProxy
=======
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.wuyan.mall.mapper")
@EnableTransactionManagement
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf:WUYANMALL/demo01_mall/src/main/java/com/wuyan/mall/MallApplication.java
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
