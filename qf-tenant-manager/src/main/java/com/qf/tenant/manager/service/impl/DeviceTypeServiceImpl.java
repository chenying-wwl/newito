package com.qf.tenant.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import com.qf.tenant.manager.feign.DeviceTypeFeign;
import com.qf.tenant.manager.service.DeviceTypeService;
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
    public R<List> findAreaAll() {
        DeviceTypeDto dto = new DeviceTypeDto();
        R<List> result = deviceTypeFeign.findAreaAll(dto);
        return result;
    }

}
