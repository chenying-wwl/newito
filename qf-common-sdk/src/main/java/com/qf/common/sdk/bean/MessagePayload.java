
package com.qf.common.sdk.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MQTT消息载荷
 * @author 千锋健哥
 */
@Data
@Accessors(chain = true)
public class MessagePayload {
    private String payload;
    private MessageType messageType;

    public MessagePayload() {
        this.messageType = MessageType.DEFAULT;
    }

    public MessagePayload(String payload, MessageType messageType) {
        this.payload = payload;
        this.messageType = messageType;
    }
}
