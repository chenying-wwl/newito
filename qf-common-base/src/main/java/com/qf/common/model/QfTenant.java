package com.qf.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
* 租户表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "租户实体")
public class QfTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 租户用户名
    */
    @Schema(description = "租户用户名")
    private String name;

    /**
    * 租户密码
    */
    @Schema(description = "租户密码")
    private String pwd;

    /**
    * 公司名称
    */
    @Schema(description = "公司名称")
    private String companyName;

    /**
    * 公司工商执照编码
    */
    @Schema(description = "公司工商执照编码")
    private String companyCode;

    /**
    * 是否可用
    */
    @Schema(description = "是否可用: 1可用, 2不可用")
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
    * 租户key
    */
    @Schema(description = "租户key")
    private String accessKey;

    /**
    * 租户秘钥
    */
    @Schema(description = "租户秘钥")
    private String accessSecurit;

    /**
    * 审核状态,0待审核, 1通过, 2不通过
    */
    @Schema(description = "审核状态,0待审核, 1通过, 2不通过")
    private Integer auditStatus;

    @Schema(description = "租户手机号")
    private String phone;
}
