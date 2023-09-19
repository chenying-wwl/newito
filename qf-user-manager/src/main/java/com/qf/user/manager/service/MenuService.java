package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MenuDto;
import com.qf.common.model.QfMenu;
import java.util.List;

/**
 * @author 千锋健哥
 */
public interface MenuService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(MenuDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfMenu> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfMenu>> list(MenuDto dto);

    /**
     * 新增
     */
    public R<QfMenu> add(QfMenu area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfMenu> update(QfMenu area);
}
