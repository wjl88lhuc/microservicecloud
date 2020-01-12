package com.atguigu.spcd.service;

import com.atguigu.spcd.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            /**
             * 当 访问微服务的get()【这个get() 不是下面的get()方法】时，如果被访问的那个服务器停止不能访问，那么就出发下面的get() 访问。
             * @param id
             * @return
             */
            @Override
            public Dept get(long id) {
                //只演示一个方法。这里不演示下面 list() 和 add(Dept dept) 方法。
                return new Dept().setDeptno(id)
                        .setDname("该ID："+id+"没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
                        .setDb_source("no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
