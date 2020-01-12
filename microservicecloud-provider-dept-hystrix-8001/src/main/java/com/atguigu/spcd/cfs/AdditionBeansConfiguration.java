package com.atguigu.spcd.cfs;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AdditionBeansConfiguration {
    @Bean
    public ServletRegistrationBean getSvlRegistrationBean(){
        HystrixMetricsStreamServlet hmStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean sltRgstBean = new ServletRegistrationBean(hmStreamServlet);

        sltRgstBean.setLoadOnStartup(1);

        ArrayList<String> urlMappings = new ArrayList<>();
        urlMappings.add("/hystrix.stream");
        sltRgstBean.setUrlMappings(urlMappings);

        sltRgstBean.setName("HystrixMetricsStreamServlet");
        return sltRgstBean;

    }
}
