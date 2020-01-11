package com.atguigu.spcd;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_App {
    /**
     * 在主驱动类添加@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class) 就可以使用自定义的负载均衡算法，而不再使用Ribbon提供的。
     * name = "MICROSERVICECLOUD-DEPT" 表示对 eureka 上 MICROSERVICECLOUD-DEPT"的服务的访问采用负载均衡。
     * MySelfRule 就是自定义的负载均衡。
     * 自定义注意事项：
     *   MySelfRule.class的bean的配置不能在 @ComponentScan 扫描的当前包及其子包下
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class,args);
    }
}
