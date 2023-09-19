package com.qf.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Mqtt驱动启动类
 * @author 千锋健哥
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.qf"})
@EnableFeignClients(basePackages = {"com.qf.driver.feign"})
public class MqttDriverApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MqttDriverApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

