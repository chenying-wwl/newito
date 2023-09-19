package com.qf.data.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;

/**
 * 设备物模型数据存储
 * @author 千锋健哥
 */
public interface DeviceModelValueService {

    /**
     * 保存设备数据
     * @param deviceModelValue
     */
    public void saveDeviceModelValue(DeviceModelValue deviceModelValue);


    /**
     * 查询设备数据
     * @param deviceModelValueDto
     * @return
     */
    Page<DeviceModelValue> list(DeviceModelValueDto deviceModelValueDto);

}
