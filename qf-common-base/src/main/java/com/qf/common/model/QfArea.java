package com.qf.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
* 行政区划表
*
* @author 千锋健哥
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "行政区实体")
public class QfArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 行政区划代码
    */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "行政区划代码, 唯一ID")
    private Integer code;

    /**
    * 名字
    */
    @Schema(description = "行政区名称")
    private String name;

    /**
    * 等级:1-省级;2-地级市;3-区/县;4-乡/镇
    */
    @Schema(description = "等级:1-省级;2-地级市;3-区/县;4-乡/镇")
    private Integer level;

    /**
    * 类型:1-省;2-自治区;3-直辖市;4-特别行政区;5-地级市;6-地区;7-自治州;8-盟;9-市辖区;10-县;11- 县级市;12-自治县;13-旗;14-自治旗;15-特区;16-林区
    */
    @Schema(description = "类型:1-省;2-自治区;3-直辖市;4-特别行政区;5-地级市;6-地区;7-自治州;8-盟;9-市辖区;10-县;11- 县级市;12-自治县;13-旗;14-自治旗;15-特区;16-林区")
    private Integer type;

    /**
    * 简称
    */
    @Schema(description = "简称")
    private String abname;

    /**
    * 所属行政区划代码
    */
    @Schema(description = "父行政区划代码")
    private Integer pid;

    /**
    * 所属行政区划名字
    */
    @Schema(description = "所属行政区划名字")
    private String pname;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String note;

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

}
