package com.qf.tenant.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import com.qf.tenant.manager.service.DeviceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备类型管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/tenant/device-type")
@Tag(name = "DeviceTypeController", description = "设备类型管理")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    /**
     * 根据条件查询所有
     * @return
     */
    @GetMapping("/all")
    @Operation(summary = "设备类型接口 - 查询所有",description = "根据条件查询所有设备类型")
    public R<List> findAreaAll() {
        R<List> result = deviceTypeService.findAreaAll();
        return result;
    }

}
