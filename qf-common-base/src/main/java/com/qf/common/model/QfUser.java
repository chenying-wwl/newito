package com.qf.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

import javax.management.relation.Role;
import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* 管理员用户表
*
* @author 千锋健哥
* @since 2023-04-18
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "管理员用户实体")
public class QfUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 昵称
    */
    @Schema(description = "昵称")
    private String nickName;

    /**
    * 真实名字
    */
    @Schema(description = "真实名字")
    private String realName;

    /**
    * 用户名，需要加密存储，可用于登录
    */
    @Schema(description = "用户名，需要加密存储，可用于登录")
    private String userName;

    /**
    * 密码，需要加密存储
    */
    @Schema(description = "密码，需要加密存储")
    private String password;

    /**
    * 手机号，需要加密存储，可用于登录
    */
    @Schema(description = "手机号，需要加密存储，可用于登录")
    private Integer phone;

    /**
    * 邮箱，需要加密存储，可用于登录
    */
    @Schema(description = "邮箱，需要加密存储，可用于登录")
    private String email;

    /**
    * 是否可用
    */
    @Schema(description = "是否可用")
    private Integer enable;

    /**
    * 描述
    */
    @Schema(description = "描述")
    private String description;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    /**
    * 修改时间
    */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date updateTime;

    /**
    * 逻辑删标识
    */
    @Schema(description = "逻辑删标识")
    private Integer deleted;

    /**
     * 此用户角色集合
     */
    @TableField(exist = false)
    @Schema(description = "该用户角色集合")
    private List<Role> roleList;

    /**
     * 此用户菜单集合
     */
    @TableField(exist = false)
    @Schema(description = "该用户菜单集合")
    private List<Menu> menuList;
}
