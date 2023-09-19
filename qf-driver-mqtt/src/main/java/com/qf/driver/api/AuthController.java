package com.qf.driver.api;

import com.qf.common.bean.R;
import com.qf.common.constant.CommonConstant;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DriverMqttDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfDevice;
import com.qf.driver.feign.DeviceFeign;
import com.qf.driver.feign.ProductFeign;
import com.qf.driver.mqtt.bean.MqttProperties;
import com.qf.driver.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 硬件设备认证对接
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/driver/auth")
@Tag(name = "AuthController", description = "设备认证管理")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 设备认证并返回mqtt服务器信息
     * @param dto
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "设备认证",description = "设备认证并返回mqtt服务器信息")
    public R<DriverMqttDto> login(@RequestBody DriverMqttDto dto) {
        log.debug("=====[设备认证]设备认证开始:=====DriverMqttDto={}", dto);
        /**
         * 校验参数
         */
        if (dto == null) {
            return R.fail("请传入认证参数!");
        }
        if (StringUtils.isEmpty(dto.getProductKey())
                || StringUtils.isEmpty(dto.getDeviceKey())
                || StringUtils.isEmpty(dto.getDeviceSecret())) {
            return R.fail("产品Key, 设备Key或者设备秘钥为空!");
        }

        R<DriverMqttDto> result = authService.login(dto);
        log.debug("=====[设备认证]设备认证结束:=====DriverMqttDto={}", dto);
        return result;
    }
}
