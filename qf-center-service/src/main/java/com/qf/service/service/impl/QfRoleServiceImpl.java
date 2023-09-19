package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.dto.RoleDto;
import com.qf.common.mapper.QfRoleMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfRole;
import com.qf.service.service.IQfRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfRoleServiceImpl implements IQfRoleService {

    @Autowired
    private QfRoleMapper roleMapper;

    @Override
    public QfRole add(QfRole type) {
        roleMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = roleMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfRole update(QfRole type) {
        roleMapper.updateById(type);
        return type;
    }

    @Override
    public QfRole selectById(String id) {
        QfRole role = roleMapper.selectById(id);
        return role;
    }

    @Override
    public Page<QfRole> list(RoleDto dto) {
        return null;
    }

    @Override
    public List<QfRole> all(RoleDto dto) {
        return null;
    }

    @Override
    public LambdaQueryWrapper<QfRole> fuzzyQuery(RoleDto dto) {
        LambdaQueryWrapper<QfRole> queryWrapper = Wrappers.<QfRole>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfRole::getName, dto.getName());
        }
        return queryWrapper;
    }

}
