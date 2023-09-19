package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDeviceTopic;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * Topic主题 Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceTopicDto extends QfDeviceTopic implements Converter<QfDeviceTopic, DeviceTopicDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfDeviceTopic d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfDeviceTopic d) {
        BeanUtils.copyProperties(d, this);
    }

}
