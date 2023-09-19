package com.qf.tenant.manager.feign;

import com.qf.common.bean.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "qf-center-data")
public interface DeviceHeartBeatFeign {

    @GetMapping("/data/heartBeat/deviceState/{productKey}")
    public R<Integer> onLineCount(@PathVariable(value = "productKey") String productKey);

    @GetMapping("/data/heartBeat/deviceState/{productKey}/{deviceKey}")
    public R<Boolean> deviceState(@PathVariable(value = "productKey") String productKey,
                                  @PathVariable(value = "deviceKey") String deviceKey);

}
