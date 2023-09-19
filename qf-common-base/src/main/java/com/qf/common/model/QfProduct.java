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
* 产品表
*
* @author 千锋健哥
* @since 2023-04-18
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "产品实体")
public class QfProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 产品名称
    */
    @Schema(description = "产品名称")
    private String name;

    /**
    * 产品标识
    */
    @Schema(description = "产品标识")
    private String productKey;

    /**
    * 所属的租户
    */
    @Schema(description = "所属的租户")
    private String tenantId;

    /**
    * 所属行政区编码
    */
    @Schema(description = "所属行政区编码")
    private Integer areaCode;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    private String username;

    /**
    * 密码
    */
    @Schema(description = "密码")
    private String password;

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


}
