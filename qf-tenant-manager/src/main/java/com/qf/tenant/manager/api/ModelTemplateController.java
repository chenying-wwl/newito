package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.tenant.manager.service.DeviceService;
import com.qf.tenant.manager.service.ModelTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tenant/model-template")
@Tag(name = "ModelTemplateController", description = "设备物模型模板")
public class ModelTemplateController {

    @Autowired
    private ModelTemplateService modelTemplateService;

    @GetMapping("/list/{deviceTypeId}")
    public R list(@PathVariable("deviceTypeId") Long deviceTypeId){
        R r = modelTemplateService.listModelTemplateByDeviceType(deviceTypeId);
        return r;
    }

}
