package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.MenuDto;
import com.qf.common.mapper.QfMenuMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfMenu;
import com.qf.service.service.IQfMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfMenuServiceImpl implements IQfMenuService {

    @Autowired
    private QfMenuMapper menuMapper;

    @Override
    public QfMenu add(QfMenu type) {
        menuMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = menuMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfMenu update(QfMenu type) {
        menuMapper.updateById(type);
        return type;
    }

    @Override
    public QfMenu selectById(String id) {
        QfMenu menu = menuMapper.selectById(id);
        return menu;
    }

    @Override
    public Page<QfMenu> list(MenuDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfMenu> page = menuMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfMenu> all(MenuDto dto) {
        List<QfMenu> list = menuMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfMenu> fuzzyQuery(MenuDto dto) {
        LambdaQueryWrapper<QfMenu> queryWrapper = Wrappers.<QfMenu>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfMenu::getName, dto.getName());
            queryWrapper.eq(dto.getParentId() != null, QfMenu::getParentId, dto.getParentId());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getPerms()), QfMenu::getPerms, dto.getPerms());
            queryWrapper.eq(dto.getType() != null, QfMenu::getType, dto.getType());
        }
        return queryWrapper;
    }

}
