package com.qf.user.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 管理中心服务入口
 *
 * @author 千锋健哥
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.qf"})
@EnableFeignClients(basePackages = {"com.qf.user.manager.feign"})
public class UserManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}

