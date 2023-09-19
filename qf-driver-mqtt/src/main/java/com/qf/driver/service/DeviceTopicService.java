package com.qf.driver.service;

import com.qf.common.model.QfDeviceTopic;

import java.util.List;

/**
 * @author 千锋健哥
 */
public interface DeviceTopicService {

    /**
     * 查询所有Topic
     * @return
     */
    public List<QfDeviceTopic> findTopicAll();

    /**
     * 根据设备Key查询对应的Topic列表
     * @param deviceKey
     * @return
     */
    public List<QfDeviceTopic> findTopicByDeviceKey(String deviceKey);

    /**
     * 订阅Topic主题
     */
    public void subscribeDeviceTopic(String deviceKey);
}
