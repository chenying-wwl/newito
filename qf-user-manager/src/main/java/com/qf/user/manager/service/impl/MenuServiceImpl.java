package com.qf.user.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MenuDto;
import com.qf.common.model.QfMenu;
import com.qf.user.manager.feign.MenuFeign;
import com.qf.user.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuFeign menuFeign;

    @Override
    public R<List> findAreaAll(MenuDto dto) {
        R<List> result = menuFeign.findAreaAll(dto);

        if (result.getData() != null && result.getData().size() > 0) {
            List<QfMenu> data = BeanUtil.copyToList(result.getData(), QfMenu.class);
            for (QfMenu qfMenu : data) {
                dto.setParentId(qfMenu.getId());
                R<List> children = menuFeign.findAreaAll(dto);
                if (children.getData() != null && children.getData().size() > 0) {
                    qfMenu.setChildren(children.getData());
                }
            }
        }
        return result;
    }

    @Override
    public R<QfMenu> selectById(String id) {
        R<QfMenu> result = menuFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfMenu>> list(MenuDto dto) {
        R<Page<QfMenu>> result = menuFeign.list(dto);
        return result;
    }

    @Override
    public R<QfMenu> add(QfMenu area) {
        R<QfMenu> result = menuFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = menuFeign.delete(id);
        return result;
    }

    @Override
    public R<QfMenu> update(QfMenu area) {
        R<QfMenu> result = menuFeign.update(area);
        return result;
    }

}
