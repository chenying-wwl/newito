package com.qf.user.manager.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 设备物模型
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface DeviceModelFeign {

    /**
     * 查询设备的物模型
     * @param dto
     * @return
     */
    @PostMapping("/service/device-model/all")
    public R<List> findAll(@RequestBody(required = false) DeviceModelDto dto);
}
