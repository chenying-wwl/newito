package com.qf.data.listener;

import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.common.utils.JsonUtil;
import com.qf.data.service.DeviceCmdService;
import com.qf.data.service.DeviceModelValueService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据中心：接收RabbitMQ中设备指令存储到MongoDB
 * @author 千锋健哥
 */
@Slf4j
@Component
public class DeviceCmdReceiver {

    @Autowired
    private DeviceCmdService deviceCmdService;


    /**
     * 接收MQTT驱动发送过来的指令
     */
    @RabbitHandler
    @RabbitListener(queues = {"qf.queue.value.cmd"})
    public void deviceCmdReceive(Channel channel, Message message, DeviceCmd deviceCmd){
        try {
            log.info("接收消息:" + JsonUtil.toJsonString(deviceCmd));
            deviceCmdService.saveDeviceCmd(deviceCmd);

            //手动应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
