package com.qf.data.service;

import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;

/**
 * @author 千锋健哥
 */
public interface RepositoryHandleService {

    /**
     * 设备发给平台的数据保存
     *
     */
    void save(DeviceModelValue deviceModelValue);

    /**
     * 平台发给设备的指令保存
     * @param deviceCmd
     */
    void saveCmd(DeviceCmd deviceCmd);


}
