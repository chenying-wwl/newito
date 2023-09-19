package com.qf.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台发给设备的指令实体
 * @author 千锋健哥
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "MongoDB设备物模型指令")
public class DeviceCmd implements Serializable {

    @MongoId
    @Schema(description = "唯一ID")
    private String id;

    @Schema(description = "租户唯一ID")
    private String tenantId;

    @Schema(description = "产品Key")
    private String productKey;

    @Schema(description = "设备Key")
    private String deviceKey;

    @Schema(description = "设备中传感器或者继电器编号")
    private Integer deviceSubCode;

    @Schema(description = "设备指令名称")
    private String cmdName;

    @Schema(description = "设备指令值")
    private String value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;
}
