package com.qf.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qf.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* 字典数据表
*
* @author 千锋健哥
* @since 2023-04-18
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class QfSysDictData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 字典编码
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictCode;

    /**
    * 字典排序
    */
    private Integer dictSort;

    /**
    * 字典标签
    */
    private String dictLabel;

    /**
    * 字典键值
    */
    private String dictValue;

    /**
    * 字典类型
    */
    private String dictType;

    /**
    * 样式属性（其他样式扩展）
    */
    private String cssClass;

    /**
    * 表格回显样式
    */
    private String listClass;

    /**
    * 是否默认（Y是 N否）
    */
    private String isDefault;

    /**
    * 状态（0正常 1停用）
    */
    private String status;

    /**
    * 创建者
    */
    private String createBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date createTime;

    /**
    * 更新者
    */
    private String updateBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern = CommonConstant.Time.COMPLETE_DATE_FORMAT, timezone = CommonConstant.Time.TIMEZONE)
    private Date updateTime;

    /**
    * 备注
    */
    private String remark;


}
