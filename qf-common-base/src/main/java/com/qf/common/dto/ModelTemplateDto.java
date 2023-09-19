package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfModelTemplate;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 物模型模板Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ModelTemplateDto extends QfModelTemplate implements Converter<QfModelTemplate, ModelTemplateDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfModelTemplate d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfModelTemplate d) {
        BeanUtils.copyProperties(d, this);
    }
}
