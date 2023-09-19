package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDeviceType;
import com.qf.service.service.IQfDeviceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备类型表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/device-type")
@Tag(name = "QfDeviceTypeController", description = "设备类型管理")
public class QfDeviceTypeController {

    @Autowired
    private IQfDeviceTypeService deviceTypeService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "设备类型接口 - 查询所有",description = "根据条件查询所有设备类型")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceTypeDto dto) {
        List<QfDeviceType> list = deviceTypeService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "设备类型接口 - 根据id查询",description = "根据ID查询")
    public R<QfDeviceType> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfDeviceType deviceType = deviceTypeService.selectById(id);
        return R.ok(deviceType);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "设备类型接口 - 分页查询",description = "分页查询")
    R<Page<QfDeviceType>> list(@RequestBody(required = false) DeviceTypeDto dto) {
        Page<QfDeviceType> page = deviceTypeService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "设备类型接口 - 新增",description = "新增")
    public R<QfDeviceType> add(@RequestBody  QfDeviceType deviceType) {
        QfDeviceType add = deviceTypeService.add(deviceType);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "设备类型接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = deviceTypeService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "设备类型接口 - 修改",description = "修改")
    public R<QfDeviceType> update(@RequestBody QfDeviceType deviceType) {
        QfDeviceType update = deviceTypeService.update(deviceType);
        return R.ok(update);
    }
}
