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
* 设备类型表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "设备类型实体")
public class QfDeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 所属行业ID
    */
    @Schema(description = "所属行业ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long industryId;

    /**
    * 设备类型名称, 例如:光照传感器
    */
    @Schema(description = "设备类型名称, 例如:光照传感器")
    private String name;

    /**
    * 类型：1.网关；2.传感器；3.继电器；
    */
    @Schema(description = "类型：1.网关；2.传感器；3.继电器；4.摄像头")
    private Integer deviceType;

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
    private Boolean deleted;


}
