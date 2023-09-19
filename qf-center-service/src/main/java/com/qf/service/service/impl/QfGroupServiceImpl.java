package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.GroupDto;
import com.qf.common.mapper.QfGroupMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfGroup;
import com.qf.service.service.IQfGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户设备分组表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfGroupServiceImpl implements IQfGroupService {

    @Autowired
    private QfGroupMapper groupMapper;

    @Override
    public QfGroup add(QfGroup type) {
        groupMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = groupMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfGroup update(QfGroup type) {
        groupMapper.updateById(type);
        return type;
    }

    @Override
    public QfGroup selectById(String id) {
        QfGroup group = groupMapper.selectById(id);
        return group;
    }

    @Override
    public Page<QfGroup> list(GroupDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfGroup> page = groupMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfGroup> all(GroupDto dto) {
        List<QfGroup> list = groupMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfGroup> fuzzyQuery(GroupDto dto) {
        LambdaQueryWrapper<QfGroup> queryWrapper = Wrappers.<QfGroup>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getGroupName()), QfGroup::getGroupName, dto.getGroupName());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getDescription()), QfGroup::getDescription, dto.getDescription());
        }
        return queryWrapper;
    }

}
