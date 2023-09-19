package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * MongoDB 设备指令
 *
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class DeviceCmdDto extends DeviceCmd implements Converter<DeviceCmd, DeviceCmdDto> {
    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(DeviceCmd deviceCmd) {
        BeanUtils.copyProperties(this, deviceCmd);
    }

    @Override
    public void convertDoToDto(DeviceCmd deviceCmd) {
        BeanUtils.copyProperties(deviceCmd, this);
    }


}
