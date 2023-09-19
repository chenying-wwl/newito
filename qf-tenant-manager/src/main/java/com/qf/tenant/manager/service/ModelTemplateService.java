package com.qf.tenant.manager.service;

import com.qf.common.bean.R;

public interface ModelTemplateService {

    /**
     * 根据设备类型查询物模型模板列表
     */
    public R listModelTemplateByDeviceType(Long deviceTypeId);

}
