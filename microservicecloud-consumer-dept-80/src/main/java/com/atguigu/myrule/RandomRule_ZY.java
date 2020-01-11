package com.atguigu.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义这个负载均衡的需求：要求轮询，但是每一个服务器都是连续访问 5 次
 */
public class RandomRule_ZY extends AbstractLoadBalancerRule {

    private Integer count = new Integer(0);
    private Server currentServer = null;
    private Lock serverChooseLock= new ReentrantLock();
    private int currentIndex = 0;

    public RandomRule_ZY() {

    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
         return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer loadBalancer, Object key) {
        if (loadBalancer == null){
            return null;
        }
        serverChooseLock.lock();
        Server server = null;
        try
        {
            List<Server> allList = loadBalancer.getAllServers();
            int serverCount = allList.size();
            if (serverCount == 0){
                return null;
            }
            if (currentServer != null && count.intValue() < 5 && allList.contains(currentServer)){
                count++;
                return currentServer;
            }
            List<Server> upList = loadBalancer.getReachableServers();
            do{
                if (currentServer == null){
                    currentIndex = 0; //第一次才会出现这一种情况
                }else{
                    currentIndex= (++currentIndex) % serverCount;
                }
                server = upList.get(currentIndex);
            }while (server == currentServer);
            count = 1;
            currentServer = server;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            serverChooseLock.unlock();
        }
        return server;
    }
}

