package com.qf.driver.config;

import cn.hutool.core.util.StrUtil;
import com.qf.common.model.QfDeviceTopic;
import com.qf.common.sdk.utils.X509Util;
import com.qf.driver.mqtt.bean.MqttProperties;
import com.qf.driver.service.DeviceTopicService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * Mqtt驱动与设备之间进行交互之前的配置
 * @author 千锋健哥
 */
@Slf4j
@Configuration
@IntegrationComponentScan
@EnableConfigurationProperties({MqttProperties.class})
public class MqttConfig {

    /**
     * 注入装有配置文件的Java配置类  方便下面操作
     */
    @Resource
    private MqttProperties mqttProperties;

    @Autowired
    private DeviceTopicService deviceTopicService;

    /**
     * 入站
     * @return
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 出站
     * @return
     */
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    /**
     * 入站配置: 设备发给平台的
     * @return
     */
    @Bean
    public MessageProducer mqttInbound() {
        //获取topic中的所有Topic主题
        List<QfDeviceTopic> topicList = deviceTopicService.findTopicAll();

        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getClient()  + "_in",
                mqttClientFactory(),
                //设置Topic主题
                topicList.stream().map(QfDeviceTopic::getTopic).toArray(String[]::new));

        //设置Qos级别
        adapter.setQos(mqttProperties.getQos());
        //设置通道
        adapter.setOutputChannel(mqttInputChannel());
        adapter.setConverter(new DefaultPahoMessageConverter());
        //设置完成的超时时间
        adapter.setCompletionTimeout(mqttProperties.getCompletionTimeout());
        return adapter;
    }

    /**
     * 出站配置: 平台发给设备的
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                mqttProperties.getClient() + "_out",
                mqttClientFactory());
        //开启异步
        messageHandler.setAsync(true);
        //设置Qos级别
        messageHandler.setDefaultQos(mqttProperties.getQos());
        //设置Topic主题
        messageHandler.setDefaultTopic(mqttProperties.getDefaultSendTopic());
        return messageHandler;
    }

    /**
     * 配置连接Mqtt工厂
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /**
     * 配置连接Mqtt的连接参数
     * 工厂的连接参数
     * @return
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();

        //用户名和密码认证
        if (mqttProperties.getAuthType().equals(MqttProperties.AuthTypeEnum.USERNAME)) {
            //MQTT服务器用户名
            mqttConnectOptions.setUserName(mqttProperties.getUsername());
            //MQTT服务器密码
            mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        }

        /**
         * QoS和会话
         * 如果 Client 想接收离线消息，必须使用持久化的会话（Clean Session = 0）连接到 Broker，
         * 这样 Broker 才会存储 Client 在离线期间没有确认接收的 QoS 大于 等于1 的消息。
         */
        mqttConnectOptions.setCleanSession(false);
        //HTTPS主机名验证
        mqttConnectOptions.setHttpsHostnameVerificationEnabled(false);
        //设置MQTT服务器连接地址
        mqttConnectOptions.setServerURIs(new String[]{mqttProperties.getUrl()});
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        return mqttConnectOptions;

    }

}