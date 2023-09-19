package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.mapper.QfDeviceTypeMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDeviceType;
import com.qf.service.service.IQfDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备类型表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfDeviceTypeServiceImpl implements IQfDeviceTypeService {

    @Autowired
    private QfDeviceTypeMapper deviceTypeMapper;

    @Override
    public QfDeviceType add(QfDeviceType type) {
        deviceTypeMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = deviceTypeMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfDeviceType update(QfDeviceType type) {
        deviceTypeMapper.updateById(type);
        return type;
    }

    @Override
    public QfDeviceType selectById(String id) {
        QfDeviceType deviceType = deviceTypeMapper.selectById(id);
        return deviceType;
    }

    @Override
    public Page<QfDeviceType> list(DeviceTypeDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfDeviceType> page = deviceTypeMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfDeviceType> all(DeviceTypeDto dto) {
        List<QfDeviceType> list = deviceTypeMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfDeviceType> fuzzyQuery(DeviceTypeDto dto) {
        LambdaQueryWrapper<QfDeviceType> queryWrapper = Wrappers.<QfDeviceType>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfDeviceType::getName, dto.getName());
            queryWrapper.eq(dto.getIndustryId() != null, QfDeviceType::getIndustryId, dto.getIndustryId());
            queryWrapper.eq(dto.getDeviceType() != null, QfDeviceType::getDeviceType, dto.getDeviceType());
        }
        return queryWrapper;
    }

}
