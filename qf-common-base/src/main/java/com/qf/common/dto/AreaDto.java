package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 行政区 Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AreaDto extends QfArea implements Converter<QfArea, AreaDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfArea d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfArea d) {
        BeanUtils.copyProperties(d, this);
    }

}
