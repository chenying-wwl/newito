package com.qf.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qf.common.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* 租户物模型属性表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "租户物模型属性实体")
public class QfDeviceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 租户物模型主键
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "租户物模型主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 设备ID
    */
    @Schema(description = "设备key")
    private String deviceKey;

    /**
    * 租户id
    */
    @Schema(description = "租户id")
    private String tenantId;

    /**
    * 租户物模型属性名称
    */
    @Schema(description = "租户物模型属性名称")
    private String propertyName;

    /**
    * 物模型类型：1.属性；2.事件；3.服务
    */
    @Schema(description = "物模型类型：1.属性；2.事件；3.服务")
    private Integer modelType;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date updateTime;

    /**
    * 是否删除
    */
    @Schema(description = "是否删除")
    private Integer deleted;

    @Schema(description = "物模型描述")
    private String description;

    @Schema(description = "物模型模板ID")
    private String templateId;

}
