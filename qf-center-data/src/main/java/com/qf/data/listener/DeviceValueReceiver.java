package com.qf.data.listener;

import com.qf.common.model.DeviceModelValue;
import com.qf.common.utils.JsonUtil;
import com.qf.data.service.DeviceModelValueService;
import com.qf.data.service.HeartBeatService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据中心：接收RabbitMQ中硬件数据存储到MongoDB
 * @author 千锋健哥
 */
@Slf4j
@Component
public class DeviceValueReceiver {

    @Autowired
    private DeviceModelValueService deviceModelValueService;

    @Autowired
    private HeartBeatService heartBeatService;


    /**
     * 接收MQTT驱动发送过来的指令
     */
    @RabbitHandler
    @RabbitListener(queues = {"qf.queue.value.device"})
    public void pointValueReceive(Channel channel, Message message, DeviceModelValue deviceModelValue){
        try {
            log.info("接收消息:" + JsonUtil.toJsonString(deviceModelValue));

            //保存设备数据到存储设备
            deviceModelValueService.saveDeviceModelValue(deviceModelValue);

            //保存心跳数据到redis
            heartBeatService.SavePing(deviceModelValue.getProduct_key(), deviceModelValue.getDevice());

            //手动应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
