package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.mapper.QfModelTemplateMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfModelTemplate;
import com.qf.service.service.IQfModelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 物模型模板表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfModelTemplateServiceImpl implements IQfModelTemplateService {

    @Autowired
    private QfModelTemplateMapper modelTemplateMapper;

    @Override
    public QfModelTemplate add(QfModelTemplate type) {
        type.setCreateTime(new Date());
        type.setUpdateTime(new Date());
        modelTemplateMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = modelTemplateMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfModelTemplate update(QfModelTemplate type) {
        type.setUpdateTime(new Date());
        modelTemplateMapper.updateById(type);
        return type;
    }

    @Override
    public QfModelTemplate selectById(String id) {
        QfModelTemplate modelTemplate = modelTemplateMapper.selectById(id);
        return modelTemplate;
    }

    @Override
    public Page<QfModelTemplate> list(ModelTemplateDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfModelTemplate> page = modelTemplateMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfModelTemplate> all(ModelTemplateDto dto) {
        List<QfModelTemplate> list = modelTemplateMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfModelTemplate> fuzzyQuery(ModelTemplateDto dto) {
        LambdaQueryWrapper<QfModelTemplate> queryWrapper = Wrappers.<QfModelTemplate>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getPropertyName()), QfModelTemplate::getPropertyName, dto.getPropertyName());
            queryWrapper.like(StrUtil.isNotBlank(dto.getDescription()), QfModelTemplate::getDescription, dto.getDescription());
            queryWrapper.eq(dto.getModelType() != null, QfModelTemplate::getModelType, dto.getModelType());
            queryWrapper.eq(dto.getDeviceTypeId() != null, QfModelTemplate::getDeviceTypeId, dto.getDeviceTypeId());
        }
        return queryWrapper;
    }

}
