package com.qf.auth;

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
 * 授权中心服务启动入口
 *
 * @author 千锋健哥
 */
@SpringBootApplication
//扫描自定义的其它组件,如aop的aspect等
@ComponentScan(basePackages = {"com.qf"})
@EnableTransactionManagement
@MapperScan({"com.qf.center.auth.mapper"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.qf.auth.feign"})
public class AuthApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

