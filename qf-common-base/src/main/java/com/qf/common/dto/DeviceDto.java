package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfGroup;
import com.qf.common.model.QfProduct;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 设备Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceDto extends QfDevice  implements Converter<QfDevice, DeviceDto> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    /*设备所属产品名称*/
    private String productName;
    /*设备所属产品key*/
    private String productKey;
    /*设备类型分类id（网关、传感器）*/
    private String deviceType;
    /*设备类型名称*/
    private String deviceTypeName;

    private ProductDto productDto;

    /*设备所属分组的分组*/
    private QfGroup group;

    @Override
    public void convertDtoToDo(QfDevice d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfDevice d) {
        BeanUtils.copyProperties(d, this);
    }

}
