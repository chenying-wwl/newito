package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceModel;
import com.qf.service.service.IQfDeviceModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户物模型属性表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/device-model")
@Tag(name = "QfDeviceModelController", description = "租户物模型属性管理")
public class QfDeviceModelController {

    @Autowired
    private IQfDeviceModelService deviceModelService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "租户物模型属性接口 - 查询所有",description = "根据条件查询所有租户物模型属性")
    public R<List> findAll(@RequestBody(required = false) DeviceModelDto dto) {
        List<QfDeviceModel> list = deviceModelService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "租户物模型属性接口 - 根据id查询",description = "根据ID查询")
    public R<QfDeviceModel> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfDeviceModel deviceModel = deviceModelService.selectById(id);
        return R.ok(deviceModel);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "租户物模型属性接口 - 分页查询",description = "分页查询")
    R<Page<QfDeviceModel>> list(@RequestBody(required = false) DeviceModelDto dto) {
        Page<QfDeviceModel> page = deviceModelService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "租户物模型属性接口 - 新增",description = "新增")
    public R<QfDeviceModel> add(@RequestBody QfDeviceModel device) {
        QfDeviceModel add = deviceModelService.add(device);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "租户物模型属性接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = deviceModelService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "租户物模型属性接口 - 修改",description = "修改")
    public R<QfDeviceModel> update(@RequestBody QfDeviceModel device) {
        QfDeviceModel update = deviceModelService.update(device);
        return R.ok(update);
    }
}
