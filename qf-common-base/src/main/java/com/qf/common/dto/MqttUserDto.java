package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.MqttUser;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * mqtt服务器连接用户表
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MqttUserDto extends MqttUser implements Converter<MqttUser, MqttUserDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(MqttUser d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(MqttUser d) {
        BeanUtils.copyProperties(d, this);
    }

}
