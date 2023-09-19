package com.qf.data.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;

/**
 * 设备指令存储
 * @author 千锋健哥
 */
public interface DeviceCmdService {

    /**
     * 保存设备指令
     */
    public void saveDeviceCmd(DeviceCmd deviceCmd);


    /**
     * 查询设备指令
     * @return
     */
    Page<DeviceCmd> list(DeviceCmdDto deviceCmdDto);
}
