package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;

public interface DeviceModelValueService {

    /**
     * 查询设备物模型数据列表
     * @param deviceModelValueDto
     * @return
     */
    public R listModelValue(DeviceModelValueDto deviceModelValueDto);

}
