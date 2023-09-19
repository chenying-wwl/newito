package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import com.qf.user.manager.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/device")
@Tag(name = "DeviceController", description = "设备管理")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "设备接口 - 查询所有",description = "根据条件查询所有设备")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceDto dto) {
        if (dto == null) {
            dto = new DeviceDto();
        }
        R<List> result = deviceService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "设备接口 - 根据id查询",description = "根据ID查询")
    public R<QfDevice> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfDevice> result = deviceService.selectById(id);
        return result;
    }

    @GetMapping("/deviceKey/{deviceKey}")
    @Operation(summary = "设备接口 - 根据deviceKey查询",description = "根据deviceKey查询")
    public R<QfDevice> selectByDeviceKey(@NotNull @PathVariable(value = "deviceKey") String deviceKey) {
        R<QfDevice> result = deviceService.selectByDeviceKey(deviceKey);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "设备接口 - 分页查询",description = "分页查询")
    R<Page<QfDevice>> list(@RequestBody(required = false) DeviceDto dto) {
        if (dto == null) {
            dto = new DeviceDto();
        }
        R<Page<QfDevice>> result = deviceService.list(dto);
        return result;
    }
}
