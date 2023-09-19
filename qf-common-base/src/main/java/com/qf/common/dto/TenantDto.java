package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfTenant;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 租户Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantDto extends QfTenant implements Converter<QfTenant, TenantDto> {

    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(QfTenant d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfTenant d) {
        BeanUtils.copyProperties(d, this);
    }

}
