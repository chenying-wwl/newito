package com.qf.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 指令参数实体
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "指令参数, Iot平台指令参数")
public class CmdParamDto implements Serializable {

    @NotNull(message = "设备Key不能为空!")
    @Schema(description = "设备Key")
    private String deviceKey;

    @Schema(description = "设备中传感器或者继电器编号")
    private Integer deviceSubCode;

    @NotNull(message = "指令名称不能为空!")
    @Schema(description = "指令名称")
    private String cmdName;

    @NotBlank(message = "指令值不能为空!")
    @Schema(description = "指令值")
    private String value;
}
