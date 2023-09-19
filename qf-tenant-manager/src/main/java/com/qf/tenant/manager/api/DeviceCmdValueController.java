package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.tenant.manager.service.DeviceCmdService;
import com.qf.tenant.manager.service.DeviceModelValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/tenant/model-cmd")
@Tag(name = "DeviceCmdValueController", description = "物模型-设备指令")
public class DeviceCmdValueController {

    @Autowired
    private DeviceCmdService deviceCmdService;


    @GetMapping("/list")
    @Operation(summary = "设备接口 - 查询设备物模型设备指令",description = "平台发送给设备的指令")
    public R list(DeviceCmdDto deviceCmdDto){
        return deviceCmdService.listDeviceCmd(deviceCmdDto);
    }

}
