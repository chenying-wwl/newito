package com.qf.data.service;

import com.qf.common.model.DeviceModelValue;

/**
 * 设备心跳业务
 * @author 千锋健哥
 */
public interface HeartBeatService {

    /**
     * 将心跳数据存入Redis中, 生存时间30秒
     * @param deviceKey
     */
    public void SavePing(String productKey, String deviceKey);

    /**
     * 查看是否在线
     * @param deviceKey
     * @return
     */
    public Boolean findState(String productKey, String deviceKey);

    /**
     * 根据产品key, 查询在线设备数量
     * @param productKey
     * @return
     */
    public Integer onLineCount(String productKey);
}
