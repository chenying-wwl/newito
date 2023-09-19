package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import com.qf.user.manager.feign.DeviceModelFeign;
import com.qf.user.manager.service.DeviceModelService;
import com.qf.user.manager.service.DeviceModelValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelFeign deviceModelFeign;


    @Override
    public R<List> findAll(DeviceModelDto dto) {
        R<List> result = deviceModelFeign.findAll(dto);
        return result;
    }

}
