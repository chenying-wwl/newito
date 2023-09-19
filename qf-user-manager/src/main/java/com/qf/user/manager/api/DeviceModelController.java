package com.qf.user.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.model.QfDeviceModel;
import com.qf.user.manager.service.DeviceModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备对应的物模型服务
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/deviceModel")
@Tag(name = "DeviceModelController", description = "设备物模型服务")
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "租户物模型属性接口 - 查询所有",description = "根据条件查询所有租户物模型属性")
    public R<List> findAll(@RequestBody(required = false) DeviceModelDto dto) {
        if (dto == null) {
            dto = new DeviceModelDto();
        }
        R<List> result = deviceModelService.findAll(dto);
        return result;
    }
}
