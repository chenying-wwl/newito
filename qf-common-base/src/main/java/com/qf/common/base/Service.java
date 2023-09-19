package com.qf.common.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 基础服务类接口
 *
 * @author 千锋健哥
 */
public interface Service<T, D> {
    /**
     * 新增
     *
     * @param type Object
     * @return Object
     */
    T add(T type);

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    boolean delete(String id);

    /**
     * 更新
     *
     * @param type Object
     * @return Object
     */
    T update(T type);

    /**
     * 通过 ID 查询
     *
     * @param id Object Id
     * @return Object
     */
    T selectById(String id);

    /**
     * 获取带分页、排序
     *
     * @param dto Object Dto
     * @return Page<Object>
     */
    Page<T> list(D dto);

    /**
     * 根据条件查询列表
     * @param dto
     * @return
     */
    List<T> all(D dto);

    /**
     * 统一接口 模糊查询构造器
     *
     * @param dto Object Dto
     * @return QueryWrapper
     */
    LambdaQueryWrapper<T> fuzzyQuery(D dto);

}
