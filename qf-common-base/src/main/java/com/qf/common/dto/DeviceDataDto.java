package com.qf.common.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 硬件传感器传入的数据模型
 * @author 千锋健哥
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class DeviceDataDto implements Serializable {

    //设备Key
    private String device;

    //产品Key
    private String product_key;

    //租户ID
    private String tenant_id;

    //传感器数据
    private Map<String, String> data = new HashMap<>();
}
