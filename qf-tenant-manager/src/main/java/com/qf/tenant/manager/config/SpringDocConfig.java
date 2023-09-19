package com.qf.tenant.manager.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 千锋健哥
 */
@Configuration
@RequiredArgsConstructor
public class SpringDocConfig {

    private final DocInfo docInfo;

    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(docInfo.getTitle())
                        .description(docInfo.getDescription())
                        .version(docInfo.getVersion()))
                .externalDocs(new ExternalDocumentation().description(docInfo.getWebsiteName())
                        .url(docInfo.getWebsiteUrl()));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(docInfo.getTitle())
                .pathsToMatch("/**")
                .build();
    }
}
