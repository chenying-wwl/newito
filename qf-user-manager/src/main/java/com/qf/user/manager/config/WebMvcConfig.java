

package com.qf.user.manager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : 千锋健哥
 */
@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 解决静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").
                addResourceLocations("classpath:/static/");
    }

    /**
     * 解决跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //允许任何域名
                .allowedOriginPatterns("*")
                //允许任何方法
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                //允许任何头
                .allowedHeaders("*")
                //暴露头
                .exposedHeaders("access-control-allow-headers",
                        "access-control-allow-methods",
                        "access-control-allow-origin",
                        "access-control-max-age",
                        "X-Frame-Options")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                .maxAge(3600);
    }
}