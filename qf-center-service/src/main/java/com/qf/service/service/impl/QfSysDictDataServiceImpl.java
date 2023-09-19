package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.SysDictDataDto;
import com.qf.common.mapper.QfSysDictDataMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfSysDictData;
import com.qf.service.service.IQfSysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  字典数据服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfSysDictDataServiceImpl implements IQfSysDictDataService {

    @Autowired
    private QfSysDictDataMapper dictDataMapper;

    @Override
    public QfSysDictData add(QfSysDictData type) {
        dictDataMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = dictDataMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfSysDictData update(QfSysDictData type) {
        dictDataMapper.updateById(type);
        return type;
    }

    @Override
    public QfSysDictData selectById(String id) {
        QfSysDictData data = dictDataMapper.selectById(id);
        return data;
    }

    @Override
    public Page<QfSysDictData> list(SysDictDataDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfSysDictData> page = dictDataMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfSysDictData> all(SysDictDataDto dto) {
        List<QfSysDictData> list = dictDataMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfSysDictData> fuzzyQuery(SysDictDataDto dto) {
        LambdaQueryWrapper<QfSysDictData> queryWrapper = Wrappers.<QfSysDictData>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.eq(dto.getDictCode() != null, QfSysDictData::getDictCode, dto.getDictCode());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getDictType()), QfSysDictData::getDictType, dto.getDictType());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getRemark()), QfSysDictData::getRemark, dto.getRemark());

        }
        return queryWrapper;
    }

}
