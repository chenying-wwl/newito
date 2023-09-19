package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.constant.CommonConstant;
import com.qf.common.model.DeviceModelValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * MongoDB 设备物模型数据
 *
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class DeviceModelValueDto extends DeviceModelValue implements Converter<DeviceModelValue, DeviceModelValueDto> {
    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(DeviceModelValue deviceModelValue) {
        BeanUtils.copyProperties(this, deviceModelValue);
    }

    @Override
    public void convertDoToDto(DeviceModelValue deviceModelValue) {
        BeanUtils.copyProperties(deviceModelValue, this);
    }


}
