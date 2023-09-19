package com.qf.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.base.Converter;
import com.qf.common.bean.Pages;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfProduct;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 产品Dto
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends QfProduct implements Converter<QfProduct, ProductDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    //存储所属区域信息
    private String areaName;

    @Override
    public void convertDtoToDo(QfProduct d) {
        BeanUtils.copyProperties(this, d);
    }

    @Override
    public void convertDoToDto(QfProduct d) {
        BeanUtils.copyProperties(d, this);
    }
}
