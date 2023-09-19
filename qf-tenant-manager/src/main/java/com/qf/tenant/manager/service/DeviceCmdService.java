package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;

public interface DeviceCmdService {

    /**
     * 查询设备物模型设备指令
     * @param deviceCmdDto
     * @return
     */
    public R listDeviceCmd(DeviceCmdDto deviceCmdDto);

}
