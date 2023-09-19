package com.qf.driver.mqtt.handler;

import com.qf.common.sdk.bean.MessageHeader;
import com.qf.common.sdk.bean.MessagePayload;
import com.qf.common.sdk.bean.MessageType;
import com.qf.common.sdk.bean.MqttMessage;
import com.qf.common.utils.JsonUtil;
import com.qf.driver.service.MqttReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

/**
 * 接收Mqtt服务器数据
 * @author 千锋健哥
 */
@Slf4j
@Configuration
public class MqttReceiveHandler {

    @Autowired
    private MqttReceiveService mqttReceiveService;

    /**
     * 此处用于接收 MQTT 发送过来的数据
     * +（加号）：可以（只能）匹配一个单词
     * #（井号）：可以匹配多个单词（或者零个）
     *
     * @return MessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handlerValue() {
        return message -> {
            log.info("设备发送过来的指令是:" + message.getPayload().toString());
            log.info("设备的发送的头:" + JsonUtil.toJsonString(message.getHeaders()));
            if (message != null) {
                //设备发送过来的消息载荷
                MessagePayload messagePayload = new MessagePayload(message.getPayload().toString(), MessageType.MQTT);
                //设备发送过来的消息头
                MessageHeader messageHeader = new MessageHeader(message.getHeaders());
                //封装成对象
                MqttMessage mqttMessage = new MqttMessage(messageHeader,messagePayload);
                //封装PointValue 才能发送消息的对象
                mqttReceiveService.receiveValue(mqttMessage);

            } else {
                log.info("========设备发送来的消息为空!!!============");
            }

        };
    }
}
