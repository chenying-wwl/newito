package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 租户物模型属性Dto
 * @author 千锋健哥
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeviceModelDto extends QfDeviceModel implements Converter<QfDeviceModel, DeviceModelDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfDeviceModel d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfDeviceModel d) {
        BeanUtils.copyProperties(d, this);
    }

}
