

package com.qf.common.sdk.bean;

import lombok.NoArgsConstructor;

/**
 * 消息类型
 * @author 千锋健哥
 */
@NoArgsConstructor
public enum MessageType {
    OPC_UA,
    OPC_DA,
    MODBUS,
    PLC,
    SERIAL,
    SOCKET,
    MQTT,
    HEARTBEAT,
    DEFAULT
}