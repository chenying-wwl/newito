package com.qf.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DatabindException;
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
import java.util.Date;

/**
* 物模型模板表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "物模型模板实体")
public class QfModelTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 设备类型ID
    */
    @Schema(description = "设备类型ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deviceTypeId;

    /**
    * 模型名称
    */
    @Schema(description = "模型名称")
    private String propertyName;

    /**
    * 物模型类型：1.属性；2.事件；3.服务
    */
    @Schema(description = "物模型类型：1.属性；2.事件；3.服务")
    private Integer modelType;

    /**
    * 描述
    */
    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date updateTime;

}
