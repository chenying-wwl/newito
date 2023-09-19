package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfGroup;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 租户设备分组Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GroupDto extends QfGroup implements Converter<QfGroup, GroupDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfGroup d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfGroup d) {
        BeanUtils.copyProperties(d, this);
    }
}
