package com.qf.driver.service;

import com.qf.common.bean.R;
import com.qf.common.dto.DriverMqttDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 设备认证
 * @author 千锋健哥
 */
public interface AuthService {

    /**
     * 设备认证接口
     * @param dto
     * @return
     */
    public R<DriverMqttDto> login(DriverMqttDto dto);
}
