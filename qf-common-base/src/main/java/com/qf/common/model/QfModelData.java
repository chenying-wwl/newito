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
* 租户物模型属性数据表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "租户物模型属性数据实体(废止)")
public class QfModelData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 租户物模型主键
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
    * 设备ID
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deviceId;

    /**
    * 物模型类型：1.读取型；2.写入型；3.读写型
    */
    private Integer type;

    /**
    * 租户id。-1表示系统内置物模型
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;

    /**
    * 租户物模型属性名称
    */
    private String propertyName;

    /**
    * 租户物模型属性值
    */
    private String propertyValue;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    /**
    * 是否删除
    */
    private Integer deleted;


}
