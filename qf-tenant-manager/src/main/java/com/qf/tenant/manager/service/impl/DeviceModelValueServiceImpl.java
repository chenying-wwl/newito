package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.tenant.manager.feign.DeviceModelValueFeign;
import com.qf.tenant.manager.service.DeviceModelValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceModelValueServiceImpl implements DeviceModelValueService {

    @Autowired
    private DeviceModelValueFeign deviceModelValueFeign;

    @Override
    public R listModelValue(DeviceModelValueDto deviceModelValueDto) {
        return deviceModelValueFeign.list(deviceModelValueDto);
    }
}
