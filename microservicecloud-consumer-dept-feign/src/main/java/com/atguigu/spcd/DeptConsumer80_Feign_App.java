package com.atguigu.spcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages= {"com.atguigu.spcd"})
public class DeptConsumer80_Feign_App {
    /**
     * Feign 绑定的算法是轮询
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class,args);
    }
}
