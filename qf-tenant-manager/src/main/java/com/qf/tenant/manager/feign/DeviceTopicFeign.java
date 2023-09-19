package com.qf.tenant.manager.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.model.QfDeviceTopic;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "qf-center-service")
public interface DeviceTopicFeign {

    @PostMapping("/service/device-topic/add")
    public R<QfDeviceTopic> add(@RequestBody QfDeviceTopic device);

    @PostMapping("/service/device-topic/all")
    public R<List> findAll(@RequestBody(required = false) DeviceTopicDto dto);

}
