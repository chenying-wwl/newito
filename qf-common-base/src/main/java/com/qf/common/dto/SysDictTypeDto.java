package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfSysDictType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeDto extends QfSysDictType implements Converter<QfSysDictType, SysDictTypeDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfSysDictType d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfSysDictType d) {
        BeanUtils.copyProperties(d, this);
    }
}
