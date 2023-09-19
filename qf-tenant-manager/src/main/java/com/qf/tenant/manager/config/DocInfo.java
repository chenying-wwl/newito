package com.qf.tenant.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 在线接口文档信息
 * @author 千锋健哥
 */
@Data
@Component
@ConfigurationProperties(prefix = "doc-info")
public class DocInfo {

    private String title = "智慧物联云平台";
    private String description = "千锋教育Java学科智慧物联云平台";
    private String version = "v2.0.0";
    private String websiteName = "千锋教育Java学科";
    private String websiteUrl = "http://www.qfedu.com/java/";
}
