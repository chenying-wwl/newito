package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.model.QfDeviceModel;

public interface DeviceModelService {

    /**
     * 添加设备物模型
     * @param qfDeviceModel
     * @return
     */
    public R add(QfDeviceModel qfDeviceModel);

    /**
     * 修改设备物模型逻辑删除状态
     * @param id
     * @param deleted
     * @return
     */
    public R updateDeleted(Long id, Integer deleted);

    R delete(Long id);

    R listDeviceModelByDeviceKey(String deviceKey);
}
