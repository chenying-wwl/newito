package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.tenant.manager.feign.DeviceCmdFeign;
import com.qf.tenant.manager.service.DeviceCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceCmdServiceImpl implements DeviceCmdService {

    @Autowired
    private DeviceCmdFeign deviceCmdFeign;

    @Override
    public R listDeviceCmd(DeviceCmdDto deviceCmdDto) {
        return deviceCmdFeign.list(deviceCmdDto);
    }
}
