package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;

/**
 * @author 千锋健哥
 */
public interface DeviceModelValueService {

    /**
     * 查询设备上报数据
     * @param deviceModelValueDto
     * @return
     */
    public R<Page<DeviceModelValue>> list(DeviceModelValueDto deviceModelValueDto);
}
