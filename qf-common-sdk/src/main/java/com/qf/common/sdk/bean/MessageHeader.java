

package com.qf.common.sdk.bean;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;
import java.util.UUID;

/**
 * MQTT消息头
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MessageHeader implements Serializable {

    private String id;
    private Integer mqttId;
    private Integer mqttReceivedQos;
    private String mqttReceivedTopic;
    private Boolean mqttDuplicate;
    private Boolean mqttReceivedRetained;
    private Long timestamp;

    public MessageHeader(MessageHeaders messageHeaders) {
        if (ObjectUtil.isNotNull(messageHeaders)) {
            try {
                UUID id = messageHeaders.get("id", UUID.class);
                if (ObjectUtil.isNotNull(id)) {
                    this.id = id.toString();
                }
            } catch (Exception ignored) {
            }
            try {
                this.mqttId = messageHeaders.get("mqtt_id", Integer.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedQos = messageHeaders.get("mqtt_receivedQos", Integer.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedTopic = messageHeaders.get("mqtt_receivedTopic", String.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttDuplicate = messageHeaders.get("mqtt_duplicate", Boolean.class);
            } catch (Exception ignored) {
            }
            try {
                this.mqttReceivedRetained = messageHeaders.get("mqtt_receivedRetained", Boolean.class);
            } catch (Exception ignored) {
            }
            try {
                this.timestamp = messageHeaders.get("timestamp", Long.class);
            } catch (Exception ignored) {
            }
        }
    }
}
