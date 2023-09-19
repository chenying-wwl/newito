package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.mapper.QfDeviceModelMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDeviceModel;
import com.qf.service.service.IQfDeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户物模型属性表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfDeviceModelServiceImpl implements IQfDeviceModelService {

    @Autowired
    private QfDeviceModelMapper deviceModelMapper;

    @Override
    public QfDeviceModel add(QfDeviceModel type) {
        deviceModelMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = deviceModelMapper.deleteById(id);
        return i > 0;
    }

    @Override
    public QfDeviceModel update(QfDeviceModel type) {
        deviceModelMapper.updateById(type);
        return type;
    }

    @Override
    public QfDeviceModel selectById(String id) {
        QfDeviceModel deviceModel = deviceModelMapper.selectById(id);
        return deviceModel;
    }

    @Override
    public Page<QfDeviceModel> list(DeviceModelDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfDeviceModel> page = deviceModelMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfDeviceModel> all(DeviceModelDto dto) {
        List<QfDeviceModel> list = deviceModelMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfDeviceModel> fuzzyQuery(DeviceModelDto dto) {
        LambdaQueryWrapper<QfDeviceModel> queryWrapper = Wrappers.<QfDeviceModel>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getPropertyName()), QfDeviceModel::getPropertyName, dto.getPropertyName());
            queryWrapper.eq(dto.getDeviceKey() != null, QfDeviceModel::getDeviceKey, dto.getDeviceKey());
            queryWrapper.eq(dto.getTenantId() != null, QfDeviceModel::getTenantId, dto.getTenantId());
            queryWrapper.eq(dto.getModelType() != null, QfDeviceModel::getModelType, dto.getModelType());
        }
        return queryWrapper;
    }

}
