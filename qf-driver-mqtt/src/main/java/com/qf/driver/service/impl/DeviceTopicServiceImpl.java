package com.qf.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.model.QfDeviceTopic;
import com.qf.driver.feign.DeviceTopicFeign;
import com.qf.driver.mqtt.bean.MqttProperties;
import com.qf.driver.service.DeviceTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
@Slf4j
public class DeviceTopicServiceImpl implements DeviceTopicService, BeanFactoryAware {

    @Autowired
    private DeviceTopicFeign deviceTopicFeign;

    private BeanFactory beanFactory;

    @Autowired
    private MqttProperties mqttProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public List<QfDeviceTopic> findTopicAll() {
        R<List> result = deviceTopicFeign.findAreaAll(new DeviceTopicDto());
        List<QfDeviceTopic> topicList = new ArrayList<>();
        if (result.getData() != null) {
            for (Object obj : result.getData()) {
                topicList.add(BeanUtil.toBean(obj, QfDeviceTopic.class));
            }
        }
        return topicList;
    }

    @Override
    public List<QfDeviceTopic> findTopicByDeviceKey(String deviceKey) {
        DeviceTopicDto dto = new DeviceTopicDto();
        dto.setDeviceKey(deviceKey);
        R<List> result = deviceTopicFeign.findAreaAll(dto);
        List<QfDeviceTopic> topicList = new ArrayList<>();
        if (result.getData() != null) {
            for (Object obj : result.getData()) {
                topicList.add(BeanUtil.toBean(obj, QfDeviceTopic.class));
            }
        }
        return topicList;
    }

    @Override
    public void subscribeDeviceTopic(String deviceKey) {
        List<QfDeviceTopic> topicList = findTopicByDeviceKey(deviceKey);

        //获取MqttConfig初始化类中的主题订阅对象, 此对象无法自动注入
        Object mqttInbound = beanFactory.getBean("mqttInbound");
        MqttPahoMessageDrivenChannelAdapter mqttSubscriber = (MqttPahoMessageDrivenChannelAdapter)mqttInbound;
        if (topicList != null) {
            for (QfDeviceTopic deviceTopic : topicList) {
                //根据数据库主题表数据订阅Topic
                try {
                    mqttSubscriber.addTopic(deviceTopic.getTopic(), mqttProperties.getQos());
                } catch (MessagingException e) {
                    log.info("=====此Topic主题已经订阅======" + deviceTopic.getTopic());
                }
            }
        }

    }



}
