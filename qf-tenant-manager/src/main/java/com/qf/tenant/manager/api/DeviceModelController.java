package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.model.QfDeviceModel;
import com.qf.tenant.manager.service.DeviceModelService;
import com.qf.tenant.manager.service.DeviceTopicService;
import io.swagger.v3.oas.annotations.Operation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tenant/device-model")
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;

    @PostMapping("/add")
    @Operation(summary = "设备物模型接口 - 添加物模型",description = "给设备添加物模型")
    public R add(@RequestBody QfDeviceModel qfDeviceModel){
        R r = deviceModelService.add(qfDeviceModel);
        return r;
    }

    @DeleteMapping("/unbind/{id}")
    @Operation(summary = "设备物模型接口 - 删除设备物模型",description = "从设备解绑物模型")
    public R delete(@PathVariable("id") Long id){
        //逻辑删除
        //R r = deviceModelService.updateDeleted(id,1);
        //物理删除
        R r = deviceModelService.delete(id);
        return r;
    }

    @PutMapping("/rebind/{id}")
    @Operation(summary = "设备物模型接口 - 重新添加设备物模型",description = "重新绑定设备物模型")
    public R rebind(@PathVariable("id") Long id){
        R r = deviceModelService.updateDeleted(id,0);
        return r;
    }

    @GetMapping("/list/{deviceKey}")
    public R listByDevice(@PathVariable("deviceKey") String deviceKey){
        return deviceModelService.listDeviceModelByDeviceKey(deviceKey);
    }

}
