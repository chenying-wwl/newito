package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.DeviceDto;
import com.qf.common.mapper.QfDeviceMapper;
import com.qf.common.mapper.QfGroupMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfGroup;
import com.qf.service.service.IQfDeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfDeviceServiceImpl implements IQfDeviceService {

    @Autowired
    private QfDeviceMapper deviceMapper;
    @Autowired
    private QfGroupMapper groupMapper;

    @Override
    public QfDevice add(QfDevice type) {
        deviceMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = deviceMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfDevice update(QfDevice type) {
        deviceMapper.updateById(type);
        return type;
    }

    @Override
    public QfDevice selectById(String id) {
        QfDevice qfDevice = deviceMapper.selectById(id);
        return qfDevice;
    }

    @Override
    public Page<QfDevice> list(DeviceDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfDevice> pageParam = new Page<QfDevice>();
        BeanUtils.copyProperties(this, dto);
        Page<QfDevice> page = deviceMapper.selectPage(pageParam, fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfDevice> all(DeviceDto dto) {
        List<QfDevice> list = deviceMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfDevice> fuzzyQuery(DeviceDto dto) {
        LambdaQueryWrapper<QfDevice> queryWrapper = Wrappers.<QfDevice>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfDevice::getName, dto.getName());
            queryWrapper.eq(dto.getDeviceTypeId() != null, QfDevice::getDeviceTypeId, dto.getDeviceTypeId());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getDeviceKey()), QfDevice::getDeviceKey, dto.getDeviceKey());
            queryWrapper.eq(dto.getProductId() != null, QfDevice::getProductId, dto.getProductId());
            queryWrapper.eq(dto.getParentId() != null, QfDevice::getParentId, dto.getParentId());
            queryWrapper.eq(dto.getTenantId() != null, QfDevice::getTenantId, dto.getTenantId());
            queryWrapper.eq(dto.getGroupId() != null, QfDevice::getGroupId, dto.getGroupId());
            queryWrapper.eq(dto.getDeleted() != null, QfDevice::getDeleted, dto.getDeleted());
            queryWrapper.eq(dto.getActive() != null, QfDevice::getActive, dto.getActive());
        }
        queryWrapper.orderByDesc(true,QfDevice::getActive, QfDevice::getCreateTime);
        return queryWrapper;
    }

    @Override
    public List<DeviceDto> listDeviceByTenantId(String tenantId) {
        List<DeviceDto> deviceDtoList = deviceMapper.listByTenantId(tenantId);
        return deviceDtoList;
    }

    @Override
    public List<DeviceDto> listDeviceByProductId(String productId) {
        List<DeviceDto> deviceDtoList = deviceMapper.listByProductId(productId);
        return deviceDtoList;
    }

    @Override
    public DeviceDto selectByDeviceId(String deviceId) {
        DeviceDto deviceDto = deviceMapper.selectByDeviceId(deviceId);
        QfGroup qfGroup = groupMapper.selectById(deviceDto.getGroupId());
        deviceDto.setGroup(qfGroup);
        return deviceDto;
    }

    @Override
    public Integer findDeviceCount(DeviceDto dto) {
        if (ObjectUtil.isNull(dto)) {
            dto = new DeviceDto();
        }
        Long count = deviceMapper.selectCount(fuzzyQuery(dto));
        return count.intValue();
    }
}
