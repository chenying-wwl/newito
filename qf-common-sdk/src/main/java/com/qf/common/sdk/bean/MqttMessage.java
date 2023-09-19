package com.qf.common.sdk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * mqtt消息实体
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MqttMessage implements Serializable {
    private MessageHeader messageHeader;
    private MessagePayload messagePayload;
}
