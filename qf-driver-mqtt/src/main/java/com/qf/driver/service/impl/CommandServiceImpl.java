package com.qf.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.hash.Hash;
import com.qf.common.bean.R;
import com.qf.common.constant.CommonConstant;
import com.qf.common.dto.CmdParamDto;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceTopic;
import com.qf.common.model.QfProduct;
import com.qf.common.utils.JsonUtil;
import com.qf.driver.feign.DeviceFeign;
import com.qf.driver.feign.ProductFeign;
import com.qf.driver.mqtt.bean.MqttProperties;
import com.qf.driver.mqtt.handler.MqttSendHandler;
import com.qf.driver.service.CommandService;
import com.qf.driver.service.DeviceTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送指令到mqtt服务器
 * @author 千锋健哥
 */
@Slf4j
@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    private MqttSendHandler mqttSendHandler;

    @Autowired
    private DeviceTopicService deviceTopicService;

    @Autowired
    private DeviceFeign deviceFeign;

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductFeign productFeign;

    @Override
    public Boolean write(CmdParamDto dto) {
        //1. 通过设备Key查询Topic列表
        List<QfDeviceTopic> topicList = deviceTopicService.findTopicByDeviceKey(dto.getDeviceKey());
        String commandTopic = null;

        //2. 组装指令
        String command = JsonUtil.toJsonString(dto);

        //3. 从列表中获取下行指令Topic
        if (topicList != null) {
            for (QfDeviceTopic deviceTopic : topicList) {
                if (deviceTopic.getTopic().contains("/qfjava/device/ctrl/")) {
                    commandTopic = deviceTopic.getTopic();
                }
            }
        }

        //4. 发送指令
        try {
            mqttSendHandler.sendToMqtt(commandTopic, mqttProperties.getQos(), command);
        } catch (Exception e) {
            mqttSendHandler.sendToMqtt(commandTopic, command);
        }

        //5. 指令存储到mongodb
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceKey(dto.getDeviceKey());
        //根据deviceKey查询设备
        R<List> deviceResult = deviceFeign.findAreaAll(deviceDto);
        if (deviceResult != null) {
            List deviceList = deviceResult.getData();
            if (deviceList != null && deviceList.size() > 0) {
                QfDevice device = BeanUtil.toBean(deviceList.get(0), QfDevice.class);
                //声明设备指令实体
                DeviceCmd deviceCmd = new DeviceCmd();
                deviceCmd.setCmdName(dto.getCmdName());
                deviceCmd.setValue(dto.getValue());
                deviceCmd.setCreateTime(new Date());
                deviceCmd.setDeviceKey(dto.getDeviceKey());
                deviceCmd.setDeviceSubCode(dto.getDeviceSubCode());
                //根据产品id查询产品实体
                R<QfProduct> productResult = productFeign.selectById(String.valueOf(device.getProductId()));
                deviceCmd.setProductKey(productResult.getData().getProductKey());
                deviceCmd.setTenantId(device.getTenantId());

                rabbitTemplate.convertAndSend(CommonConstant.Rabbit.TOPIC_EXCHANGE_VALUE,
                        CommonConstant.Rabbit.ROUTING_DEVICE_CMD_PREFIX + "cmd",
                        deviceCmd);
            }
        }

        return true;

    }

}
