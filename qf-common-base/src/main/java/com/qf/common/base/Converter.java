package com.qf.common.base;

/**
 * Converter
 *
 * @author 千锋健哥
 */
public interface Converter<DO, DTO> {
    /**
     * DTO 转 DO
     *
     * @param d Do对象
     */
    void convertDtoToDo(DO d);

    /**
     * DO 转 DTO
     *
     * @param d Do对象
     */
    void convertDoToDto(DO d);
}
