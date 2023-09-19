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
import java.util.Date;

/**
 * Mqtt服务器账户表
 * @author 千锋健哥
 * @since 2023-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Mqtt服务器账户实体")
public class MqttUser implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "唯一ID")
    private Integer id;

    /**
    * mqtt客户端连接用户名
    */
    @Schema(description = "mqtt客户端连接用户名")
    private String username;

    /**
    * mqtt客户端连接密码
    */
    @Schema(description = "mqtt客户端连接密码")
    private String password;

    /**
    * 盐值
    */
    @Schema(description = "盐值")
    private String salt;

    /**
    * 是否为超级管理员
    */
    @Schema(description = "是否为超级管理员")
    private Boolean isSuperuser;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date created;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private String tenantId;


}
