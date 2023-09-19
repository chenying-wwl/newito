package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfSysDictData;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 字典数据Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysDictDataDto extends QfSysDictData implements Converter<QfSysDictData, SysDictDataDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfSysDictData d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfSysDictData d) {
        BeanUtils.copyProperties(d, this);
    }

}
