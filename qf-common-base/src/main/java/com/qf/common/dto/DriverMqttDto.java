package com.qf.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(description = "Mqtt服务器信息")
public class DriverMqttDto {

    @Schema(description = "产品标识")
    private String productKey;

    @Schema(description = "设备标识")
    private String deviceKey;

    @Schema(description = "设备秘钥")
    private String deviceSecret;

    @Schema(description = "mqtt服务器url地址")
    private String url;

    @Schema(description = "mqtt服务器ip地址")
    private String ip;

    @Schema(description = "mqtt服务器端口号")
    private Integer port;

    @Schema(description = "mqtt服务器用户名")
    private String username;

    @Schema(description = "mqtt服务器密码")
    private String password;

    @Schema(description = "租户ID")
    private String tenantId;
}
