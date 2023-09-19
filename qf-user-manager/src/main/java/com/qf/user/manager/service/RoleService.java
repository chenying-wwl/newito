package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.RoleDto;
import com.qf.common.model.QfRole;
import java.util.List;

/**
 * @author 千锋健哥
 */
public interface RoleService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(RoleDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfRole> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfRole>> list(RoleDto dto);

    /**
     * 新增
     */
    public R<QfRole> add(QfRole area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfRole> update(QfRole area);
}
