package com.atguigu.spcd.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    //@Configuration 等同于 applicationContext.xml。类似spring里面的applicationContext.xml写入的注入Bean
    /*@Bean
    public UserService getUserService(){
        return new UserServiceImpl();
    }*/

    /**
     * RestTemplate的介绍：
     * RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，
     * 是Spring提供的用于访问Rest服务的客户端模板工具集。类似于 httpClient。
     * RestTemplate使用方式：
     * 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap, ResponseBean.class)这三个参数分别代表 
     * REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     * @return
     */
    @Bean
    @LoadBalanced  //Spring Cloud Ribbon 是基于NexFlix Ribbon 实现的一套 客户端 负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public IRule getRule(){
//        //Ribbon的负载均衡默认是轮询。如果没有提供负载均衡的IRule的实现类组成的Bean，那么就使用默认的轮询方式。、
//        //一旦提供 IRule的实现类组成的Bean 给Springboot管理，那么就不再使用默认的负载均衡算法了。
//        //除了 RoundRobinRule（轮询）与 RandomRule 之外，还 RetryRule 。
//        //RetryRule： 首先按照轮询的方式，如果其中某个节点Down掉了，
//        // 那么在访问几次失败之后，以后的轮询就跳过了该节点，直到该节点Down掉。
//        return new RandomRule();
//    }

}
