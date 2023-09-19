package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.QfModelData;
import com.qf.tenant.manager.service.DeviceModelValueService;
import com.qf.tenant.manager.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tenant/model-data")
@Tag(name = "DeviceModelValueController", description = "物模型数据")
public class DeviceModelValueController {

    @Autowired
    private DeviceModelValueService deviceModelValueService;

    @GetMapping("/list")
    @Operation(summary = "设备接口 - 查询设备物模型数据",description = "查询设备物模型数据")
    public R list(DeviceModelValueDto deviceModelValueDto){
        return deviceModelValueService.listModelValue(deviceModelValueDto);
    }

}
