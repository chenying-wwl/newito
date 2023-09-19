package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfProduct;
import com.qf.service.service.IQfAreaService;
import com.qf.service.service.IQfDeviceService;
import com.qf.service.service.IQfDeviceTypeService;
import com.qf.service.service.IQfProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/device")
@Tag(name = "QfDeviceController", description = "设备管理")
public class QfDeviceController {

    @Autowired
    private IQfDeviceService deviceService;

    /**
     * 统计设备数量
     * @param dto
     * @return
     */
    @PostMapping("/count")
    @Operation(summary = "设备接口 - 统计数量",description = "根据条件统计设备数量")
    public R<Integer> findAllCount(@RequestBody(required = false) DeviceDto dto) {
        Integer count = deviceService.findDeviceCount(dto);
        return R.ok(count);
    }

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "设备接口 - 查询所有",description = "根据条件查询所有设备")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceDto dto) {
        List<QfDevice> list = deviceService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "设备接口 - 根据id查询",description = "根据ID查询")
    public R<QfDevice> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfDevice device = deviceService.selectById(id);
        return R.ok(device);
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "设备接口 - 根据id查询",description = "根据ID查询,关联查询产品信息")
    public R<DeviceDto> selectDetailById(@NotNull @PathVariable(value = "id") String id) {
        DeviceDto deviceDto = deviceService.selectByDeviceId(id);
        return R.ok(deviceDto,"设备信息查询成功！");
    }


    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "设备接口 - 分页查询",description = "分页查询")
    R<Page<QfDevice>> list(@RequestBody(required = false) DeviceDto dto) {
        Page<QfDevice> page = deviceService.list(dto);
        return R.ok(page);
    }

    @GetMapping("/listByTenant/{tenantId}")
    @Operation(summary = "设备接口 - 根据租户id查询",description = "根据租户id查询")
    R<List<DeviceDto>> lisByTenant(@PathVariable("tenantId") String tenantId) {
        List<DeviceDto> deviceDtoList = deviceService.listDeviceByTenantId(tenantId);
        return R.ok(deviceDtoList);
    }

    @GetMapping("/listByProduct/{productId}")
    @Operation(summary = "设备接口 - 根据产品id查询",description = "根据产品id查询设备列表")
    R<List<DeviceDto>> lisByProduct(@PathVariable("productId") String productId) {
        List<DeviceDto> deviceDtoList = deviceService.listDeviceByProductId(productId);
        return R.ok(deviceDtoList);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "设备接口 - 新增",description = "新增")
    public R<QfDevice> add(@RequestBody QfDevice device) {
        QfDevice add = deviceService.add(device);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "设备接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = deviceService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "设备接口 - 修改",description = "修改")
    public R<QfDevice> update(@RequestBody QfDevice device) {
        QfDevice update = deviceService.update(device);
        return R.ok(update);
    }
}
