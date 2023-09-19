package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.tenant.manager.service.DeviceTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tenant/device-topic")
public class DeviceTopicController {

    @Autowired
    private DeviceTopicService deviceTopicService;

    @GetMapping("/list/{deviceKey}")
    public R list(@PathVariable("deviceKey") String deviceKey){
        R r = deviceTopicService.listByDeviceKey(deviceKey);
        return r;
    }

}
