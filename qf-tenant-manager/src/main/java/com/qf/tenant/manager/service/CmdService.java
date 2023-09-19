package com.qf.tenant.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.model.DeviceCmd;

/**
 * @author 千锋健哥
 */
public interface CmdService {

    /**
     * 查询设备指令日志
     * @param deviceCmdDto
     * @return
     */
    public R<Page<DeviceCmd>> list(DeviceCmdDto deviceCmdDto);
}
