package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import com.qf.user.manager.feign.DeviceTypeFeign;
import com.qf.user.manager.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeFeign deviceTypeFeign;

    @Override
    public R<List> findAreaAll(DeviceTypeDto dto) {
        R<List> result = deviceTypeFeign.findAreaAll(dto);
        return result;
    }

    @Override
    public R<QfDeviceType> selectById(String id) {
        R<QfDeviceType> result = deviceTypeFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfDeviceType>> list(DeviceTypeDto dto) {
        R<Page<QfDeviceType>> result = deviceTypeFeign.list(dto);
        return result;
    }

    @Override
    public R<QfDeviceType> add(QfDeviceType deviceType) {
        deviceType.setCreateTime(new Date());
        deviceType.setUpdateTime(new Date());
        deviceType.setDeleted(false);
        R<QfDeviceType> result = deviceTypeFeign.add(deviceType);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = deviceTypeFeign.delete(id);
        return result;
    }

    @Override
    public R<QfDeviceType> update(QfDeviceType deviceType) {
        deviceType.setUpdateTime(new Date());
        R<QfDeviceType> result = deviceTypeFeign.update(deviceType);
        return result;
    }

}
