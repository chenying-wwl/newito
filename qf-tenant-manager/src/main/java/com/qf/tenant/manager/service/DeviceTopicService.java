package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.model.QfDeviceTopic;

/**
 * Topic相关业务
 */
public interface DeviceTopicService {

    /**
     * 创建设备Topic——添加设备的Topic信息
     * @return
     */
    public R addTopic(QfDeviceTopic qfDeviceTopic);

    /**
     * 根据设备key查询当前设备的deviceTopic
     * @param deviceKey
     * @return
     */
    R listByDeviceKey(String deviceKey);
}
