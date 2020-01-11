package com.atguigu.spcd.controller;

import com.atguigu.spcd.entity.Dept;
import com.atguigu.spcd.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private DeptService deptService;

    //provides a simple API for discovery clients which can discover services registering on the Eureka
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        System.out.println("***************************");
        System.out.println(dept);
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id){
        return deptService.get(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> getAll(){
        return deptService.list();
    }

    /**
     * 通过import org.springframework.cloud.client.discovery.DiscoveryClient 获取在Eureka服务器上注册的服务
     * 也可以通过负载均衡，不必要通过DiscoveryClient。
     * @return
     */
    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discoveryAndGetServiceUrl() {
        System.out.println("applicationName: " + applicationName);
        List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
        if (list != null && list.size() > 0){
            ServiceInstance serviceInstance = list.get(0);
            System.out.println("************打印查看serviceInstance的各个字段的值************************");
            System.out.println("serviceId: " + serviceInstance.getServiceId());
            System.out.println("host:" + serviceInstance.getHost());
            System.out.println("port: " + serviceInstance.getPort());
            System.out.println("url: " + serviceInstance.getUri().toString());
        }
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri();
        }
        return null;
    }

}
