package com.qf.driver.mqtt.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;

/**
 * 加载 application.yml中配置文件信息
 * 读取MQTT连接配置信息
 * @author 千锋健哥
 */
@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "driver.mqtt")
public class MqttProperties {
    @NotBlank(message = "url can't be empty，ssl://host:port")
    private String url;

    @NotNull(message = "auth type can't be empty")
    private AuthTypeEnum authType;

    private String username;
    private String password;

    private String clientKeyPass = "qf-client";

    @Min(0)
    @Max(2)
    private Integer qos;

    @NotBlank(message = "默认发送Topic不能为空")
    private String defaultSendTopic;

    @NotBlank(message = "client name不能为空")
    private String client;

    @NotNull(message = "keep alive interval不能为空")
    private Integer keepAlive = 15;

    @NotNull(message = "完成超时时间不能为空")
    private Integer completionTimeout = 3000;


    /**
     * Mqtt 权限认证类型枚举
     */
    @NoArgsConstructor
    public enum AuthTypeEnum {
        NONE, CLIENT_ID, USERNAME, X509
    }

}
