package com.qf.driver.service.impl;


import com.qf.common.constant.CommonConstant;
import com.qf.common.dto.DeviceDataDto;
import com.qf.common.model.DeviceModelValue;
import com.qf.common.sdk.bean.MqttMessage;
import com.qf.common.utils.JsonUtil;
import com.qf.driver.service.MqttReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 将解析之后的数据封装
 * @author 千锋健哥
 */
@Slf4j
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 务必实现，单点逻辑
     * @param mqttMessage
     */
    @Override
    public void receiveValue(MqttMessage mqttMessage) {

        //判断消息不为空
        if (mqttMessage != null) {
            //获取主题topic
            String topicName = mqttMessage.getMessageHeader().getMqttReceivedTopic();
            //判断是传感器上报给平台的主题topic数据
            if (topicName.contains("/qfjava/device/data")) {
                String payload = mqttMessage.getMessagePayload().getPayload();
                try {
                    DeviceDataDto deviceDataDto = JsonUtil.parseObject(payload, DeviceDataDto.class);
                    if (deviceDataDto != null) {
                        DeviceModelValue deviceModelValue = new DeviceModelValue();
                        deviceModelValue.setCreateTime(new Date());
                        deviceModelValue.setData(deviceDataDto.getData());
                        deviceModelValue.setDevice(deviceDataDto.getDevice());
                        deviceModelValue.setProduct_key(deviceDataDto.getProduct_key());
                        deviceModelValue.setTenant_id(deviceDataDto.getTenant_id());
                        //发送到mq, 由data服务接收并存储到mongodb
                        rabbitTemplate.convertAndSend(CommonConstant.Rabbit.TOPIC_EXCHANGE_VALUE,
                                CommonConstant.Rabbit.ROUTING_DEVICE_VALUE_PREFIX + "data",
                                deviceModelValue);
                    }

                } catch (Exception e) {
                    log.info("qf-driver-mqtt转换数据失败, 传感器传入数据不符合格式要求!");
                }
                System.out.println("=======" + payload);
            }
        }
    }

    /**
     * 务必实现，批量逻辑
     * @param mqttMessageList
     */
    @Override
    public void receiveValues(List<MqttMessage> mqttMessageList) {
        mqttMessageList.forEach(this::receiveValue);
    }
}
