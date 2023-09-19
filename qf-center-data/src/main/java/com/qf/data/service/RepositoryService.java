
package com.qf.data.service;


import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;

/**
 * 设备数据存储策略服务接口
 *
 * @author 千锋健哥
 */
public interface RepositoryService {


    /**
     * 存储设备物模型数据
     * @param deviceModelValue
     */
    public void saveDeviceModelValue(DeviceModelValue deviceModelValue);

    /**
     * 存储设备指令
     * @param deviceCmd
     */
    public void saveDeviceCmd(DeviceCmd deviceCmd);
}
