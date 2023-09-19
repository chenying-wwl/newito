package com.qf.driver.service;


import com.qf.common.sdk.bean.MqttMessage;

import java.util.List;

/**
 * @author 千锋健哥
 */
public interface MqttReceiveService {

    /**
     * 接收单条消息
     * @param mqttMessage
     */
    void receiveValue(MqttMessage mqttMessage);

    /**
     * 接收消息集合
     * @param mqttMessageList
     */
    void receiveValues(List<MqttMessage> mqttMessageList);
}
