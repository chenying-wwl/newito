package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.SysDictTypeDto;
import com.qf.common.mapper.QfSysDictTypeMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfSysDictType;
import com.qf.service.service.IQfSysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfSysDictTypeServiceImpl implements IQfSysDictTypeService {

    @Autowired
    private QfSysDictTypeMapper dictTypeMapper;

    @Override
    public QfSysDictType add(QfSysDictType type) {
        dictTypeMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = dictTypeMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfSysDictType update(QfSysDictType type) {
        dictTypeMapper.updateById(type);
        return type;
    }

    @Override
    public QfSysDictType selectById(String id) {
        QfSysDictType dictType = dictTypeMapper.selectById(id);
        return dictType;
    }

    @Override
    public Page<QfSysDictType> list(SysDictTypeDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfSysDictType> page = dictTypeMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfSysDictType> all(SysDictTypeDto dto) {
        List<QfSysDictType> list = dictTypeMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfSysDictType> fuzzyQuery(SysDictTypeDto dto) {
        LambdaQueryWrapper<QfSysDictType> queryWrapper = Wrappers.<QfSysDictType>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getDictName()), QfSysDictType::getDictName, dto.getDictName());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getDictType()), QfSysDictType::getDictType, dto.getDictType());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getStatus()), QfSysDictType::getStatus, dto.getStatus());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getRemark()), QfSysDictType::getRemark, dto.getRemark());
        }
        return queryWrapper;
    }

}
