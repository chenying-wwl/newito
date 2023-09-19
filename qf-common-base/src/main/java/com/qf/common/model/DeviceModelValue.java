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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MongoDB 设备物模型数据
 *
 * @author 千锋健哥
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "MongoDB设备物模型数据")
public class DeviceModelValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @MongoId
    @Schema(description = "唯一ID")
    private String id;

    @Schema(description = "租户唯一ID")
    private String tenant_id;

    @Schema(description = "产品Key")
    private String product_key;

    @Schema(description = "设备Key")
    private String device;

    @Schema(description = "传感器数据")
    private Map<String, String> data = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;


}
