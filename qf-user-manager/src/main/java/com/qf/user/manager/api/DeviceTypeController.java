package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import com.qf.user.manager.service.DeviceTypeService;
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
@RequestMapping("/user/device-type")
@Tag(name = "DeviceTypeController", description = "设备类型管理")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "设备类型接口 - 查询所有",description = "根据条件查询所有设备类型")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceTypeDto dto) {
        if (dto == null) {
            dto = new DeviceTypeDto();
        }
        R<List> result = deviceTypeService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "设备类型接口 - 根据id查询",description = "根据ID查询")
    public R<QfDeviceType> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfDeviceType> result = deviceTypeService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "设备类型接口 - 分页查询",description = "分页查询")
    R<Page<QfDeviceType>> list(@RequestBody(required = false) DeviceTypeDto dto) {
        if (dto == null) {
            dto = new DeviceTypeDto();
        }
        R<Page<QfDeviceType>> result = deviceTypeService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "设备类型接口 - 新增",description = "新增")
    public R<QfDeviceType> add(@RequestBody  QfDeviceType deviceType) {
        R<QfDeviceType> result = deviceTypeService.add(deviceType);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "设备类型接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = deviceTypeService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "设备类型接口 - 修改",description = "修改")
    public R<QfDeviceType> update(@RequestBody QfDeviceType deviceType) {
        R<QfDeviceType> result = deviceTypeService.update(deviceType);
        return result;
    }
}
