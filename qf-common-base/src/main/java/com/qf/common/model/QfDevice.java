package com.qf.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
* 设备表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "设备实体")
public class QfDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "唯一ID")
    private Long id;

    /**
    * 设备名称
    */
    @Schema(description = "设备名称")
    private String name;

    /**
    * 设备标识
    */
    @Schema(description = "设备标识")
    private String deviceKey;

    /**
     * 设备秘钥
     */
    @Schema(description = "设备秘钥")
    private String deviceSecret;

    /**
    * 设备类型
    */
    @Schema(description = "设备类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deviceTypeId;

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

    /**
    * 所属产品
    */
    @Schema(description = "所属产品")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
    * 父设备
    */
    @Schema(description = "父设备")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
    * 租户ID
    */
    @Schema(description = "租户ID")
    private String tenantId;

    /**
    * 分组ID
    */
    @Schema(description = "分组ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;

    /**
     * 设备激活状态:1未激活, 2激活
     */
    @Schema(description = "设备激活状态:1未激活, 2激活")
    private Integer active;

    @Schema(description = "设备在产品中的唯一编号，同一个产品下的多个设备编号不能重复")
    private Integer deviceIndex;

    @Schema(description = "设备状态, true在线, false离线")
    @TableField(exist = false)
    private Boolean state;

}
