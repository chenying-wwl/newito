package com.qf.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.qf.common.bean.R;
import com.qf.common.constant.ValueConstant;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DriverMqttDto;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.MqttUser;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceTopic;
import com.qf.common.model.QfProduct;
import com.qf.common.utils.SecuretUtil;
import com.qf.driver.config.MqttConfig;
import com.qf.driver.feign.DeviceFeign;
import com.qf.driver.feign.MqttUserFeign;
import com.qf.driver.feign.ProductFeign;
import com.qf.driver.mqtt.bean.MqttProperties;
import com.qf.driver.service.AuthService;
import com.qf.driver.service.DeviceTopicService;
import com.qf.driver.service.MqttUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 千锋健哥
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    /**
     * 注入装有配置文件的Java配置类  方便下面操作
     */
    @Resource
    private MqttProperties mqttProperties;

    @Autowired
    private DeviceFeign deviceFeign;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private MqttUserService mqttUserService;

    @Autowired
    private DeviceTopicService deviceTopicService;

    @Override
    public R<DriverMqttDto> login(DriverMqttDto dto) {
        /**
         * 认证业务
         */
        //1. 校验产品Key
        ProductDto productDto = new ProductDto();
        productDto.setProductKey(dto.getProductKey());
        R<List> productResult = productFeign.findAreaAll(productDto);
        if (productResult == null) {
            log.debug("[设备认证]产品不存在:===DriverMqttDto={}" + dto);
            return R.fail("产品不存在");
        }
        if (productResult.getData() == null) {
            log.debug("[设备认证]产品不存在:===DriverMqttDto={}" + dto);
            return R.fail("产品不存在");
        }
        if (productResult.getData().size() == 0){
            log.debug("[设备认证]产品不存在:===DriverMqttDto={}" + dto);
            return R.fail("产品不存在");
        }

        //2. 校验设备Key
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceKey(dto.getDeviceKey());
        R<List> deviceResult = deviceFeign.findAreaAll(deviceDto);
        if (deviceResult == null) {
            log.debug("[设备认证]设备不存在:===DriverMqttDto={}" + dto);
            return R.fail("设备不存在");
        }
        if (deviceResult.getData() == null) {
            log.debug("[设备认证]设备不存在:===DriverMqttDto={}" + dto);
            return R.fail("设备不存在");
        }
        if (deviceResult.getData().size() == 0) {
            log.debug("[设备认证]设备不存在:===DriverMqttDto={}" + dto);
            return R.fail("设备不存在");
        }

        //3. 校验设备秘钥
        QfDevice device = BeanUtil.toBean(deviceResult.getData().get(0), QfDevice.class);
        if (!dto.getDeviceSecret().equals(device.getDeviceSecret())) {
            log.debug("[设备认证]设备秘钥错误:===DriverMqttDto={}" + dto);
            return R.fail("设备秘钥错误!");
        }

        //4. 认证成功设备激活
        device.setActive(ValueConstant.Device.ACTIVE_YES);
        deviceFeign.update(device);
        log.debug("[设备认证]设备激活成功:===DriverMqttDto={}" + dto);

        //5. 创建Mqtt服务器用户
        //查询该租户是否有mqtt服务器账号
        QfProduct qfProduct = BeanUtil.toBean(productResult.getData().get(0), QfProduct.class);
        mqttUserService.addMqttUser(qfProduct.getTenantId());
        log.debug("[设备认证]创建设备MQTT服务器账号:===DriverMqttDto={}" + dto);

        //6. 创建mqtt服务器topic并连接
        deviceTopicService.subscribeDeviceTopic(device.getDeviceKey());
        log.debug("[设备认证]创建设备MQTT服务器Topic并连接:===DriverMqttDto={}" + dto);

        /**
         * 7. 封装Mqtt服务器数据到Dto
         */
        String url = mqttProperties.getUrl();
        url = url.replaceAll("//", "");
        String[] split = url.split(":");
        dto.setUrl(url);
        dto.setIp(split[1]);
        dto.setPort(Integer.parseInt(split[2]));
        dto.setUsername(qfProduct.getTenantId());
        dto.setPassword(qfProduct.getTenantId());
        dto.setTenantId(qfProduct.getTenantId());
        return R.ok(dto);
    }

}
