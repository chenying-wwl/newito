package com.qf.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Knife4J在线文档静态资源放行
 * @author 千锋健哥
 */
@Configuration
public class Knife4jConfig implements WebMvcConfigurer {

    /**
     * 指定静态资源位置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 指定查找静态资源的位置
         */
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");

        /*文档需要映射的静态资源*/
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
