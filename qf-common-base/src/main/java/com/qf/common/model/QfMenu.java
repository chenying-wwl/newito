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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* 菜单管理
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "菜单实体")
public class QfMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @Schema(description = "唯一ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 菜单名称
    */
    @Schema(description = "菜单名称")
    private String name;

    /**
    * 父菜单ID，一级菜单为0
    */
    @Schema(description = "父菜单ID，一级菜单为0")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
    * 菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
    */
    @Schema(description = "菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)")
    private String url;

    /**
    * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
    */
    @Schema(description = "授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)")
    private String perms;

    /**
    * 类型   0：目录   1：菜单   2：按钮
    */
    @Schema(description = "类型 0:目录 1:菜单 2:按钮")
    private Integer type;

    /**
    * 菜单图标
    */
    @Schema(description = "菜单图标")
    private String icon;

    /**
    * 排序
    */
    @Schema(description = "排序")
    private Integer orderNum;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    @TableField(exist = false)
    private List<QfMenu> children;


}
