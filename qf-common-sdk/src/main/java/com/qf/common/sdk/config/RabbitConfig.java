package com.qf.common.sdk.config;

import com.qf.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 千锋健哥
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Resource
    private ConnectionFactory connectionFactory;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback((message) -> {
            log.error("Send message({}) to exchange({}), routingKey({}) failed: {}", message.getMessage(), message.getExchange(), message.getRoutingKey(), message.getReplyText());
        });
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("CorrelationData({}) ack failed: {}", correlationData, cause);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    /**
     * 创建交换机   qf.exchange.value
     * @return
     */
    @Bean
    TopicExchange valueExchange() {
        return new TopicExchange(CommonConstant.Rabbit.TOPIC_EXCHANGE_VALUE, true, false);
    }

    /**
     * 创建队列  qf.queue.value.device
     * @return
     */
    @Bean
    Queue deviceValueQueue() {
        Map<String, Object> arguments = new HashMap<>();
        // 30天： 30 * 24 * 60 * 60 * 1000 = 2592000000L
        arguments.put(CommonConstant.Rabbit.MESSAGE_TTL, 2592000000L);
        return new Queue(CommonConstant.Rabbit.QUEUE_DEVICE_VALUE, true, false, false, arguments);
    }

    /**
     * 创建队列  qf.queue.value.cmd
     * @return
     */
    @Bean
    Queue deviceCmdQueue() {
        Map<String, Object> arguments = new HashMap<>();
        // 30天： 30 * 24 * 60 * 60 * 1000 = 2592000000L
        arguments.put(CommonConstant.Rabbit.MESSAGE_TTL, 2592000000L);
        return new Queue(CommonConstant.Rabbit.QUEUE_DEVICE_CMD, true, false, false, arguments);
    }

    /**
     * 绑定交换机与队列  qf.routing.value.device.*
     *       主题模式下： 可以使用通配符
     *                *：代表一个单词
     *                #：代码多个单词或没有单词
     * @param valueExchange
     * @param deviceValueQueue
     * @return
     */
    @Bean
    Binding deviceValueBinding(TopicExchange valueExchange, Queue deviceValueQueue) {
        return BindingBuilder
                .bind(deviceValueQueue)
                .to(valueExchange)
                .with(CommonConstant.Rabbit.ROUTING_DEVICE_VALUE_PREFIX + CommonConstant.Symbol.ASTERISK);
    }

    /**
     * 绑定交换机与队列  qf.routing.value.cmd.*
     *       主题模式下： 可以使用通配符
     *                *：代表一个单词
     *                #：代码多个单词或没有单词
     */
    @Bean
    Binding deviceCmdBinding(TopicExchange valueExchange, Queue deviceCmdQueue) {
        return BindingBuilder
                .bind(deviceCmdQueue)
                .to(valueExchange)
                .with(CommonConstant.Rabbit.ROUTING_DEVICE_CMD_PREFIX + CommonConstant.Symbol.ASTERISK);
    }

}
