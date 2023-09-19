package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import com.qf.tenant.manager.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tenant/device")
@Tag(name = "DeviceController", description = "租户系统设备管理")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/parentId/{parentId}")
    @Operation(summary = "设备接口 - 根据parentId查询",description = "根据parentId查询")
    public R<List<QfDevice>> findByParentId(@NotNull @PathVariable(value = "parentId") Long parentId) {
        R<List<QfDevice>> result = deviceService.findByParentId(parentId);
        return result;
    }

    @PostMapping("/add")
    public R add(@RequestBody DeviceDto deviceDto){
        return deviceService.saveDevice(deviceDto);
    }

    @GetMapping("/list")
    @Operation(summary = "设备接口 - 多条件查询",description = "多条件查询设备列表")
    public R list(DeviceDto deviceDto){
        return deviceService.listDevices(deviceDto);
    }

    @GetMapping("/list/{productId}")
    public R list(@PathVariable("productId") Long productId){
        return deviceService.listDevices(productId);
    }

    @GetMapping("/listByTenant/{tenantId}")
    public R listByTenant(@PathVariable("tenantId") String tenantId){
        return deviceService.listDevicesByTenant(tenantId);
    }

    @GetMapping("/listByProduct/{productId}")
    public R listByProduct(@PathVariable("productId") String productId){
        return deviceService.listDevicesProduct(productId);
    }

    /**
     * 根据设备ID删除设备，需要手机验证码
     * @param deviceId
     * @param phone
     * @param code
     * @return
     */
    /*
    @DeleteMapping("/del/{id}")
    public R del(@PathVariable("id") Long deviceId,String phone, String code){
        return deviceService.delDevice(deviceId,phone,code);
    }
     */

    /**
     * 根据设备ID删除设备，不需要手机验证码
     * @param deviceId
     * @return
     */
    @DeleteMapping("/del/{id}")
    public R del(@PathVariable("id") Long deviceId){
        return deviceService.delDevice(deviceId);
    }


    @PostMapping("/join")
    @Operation(summary = "设备接口 - 添加设备到分组",description = "添加设备到分组")
    public R joinGroup(Long deviceId,Long groupId){
        return deviceService.addDeviceToGroup(deviceId,groupId);
    }

    @PostMapping("/unjoin")
    @Operation(summary = "设备接口 - 从分组移除设备",description = "从分组移除设备")
    public R unJoinGroup(Long deviceId,Long groupId){
        return deviceService.removeDeviceFromGroup(deviceId,groupId);
    }

    /**
     * 根据设备ID查询设备详情
     * @param deviceId
     * @return
     */
    @GetMapping("/get/{deviceId}")
    public R get(@PathVariable("deviceId") String deviceId){
        return deviceService.getDeviceById(deviceId);
    }

    /**
     * 根据条件统计设备数量
     */
    @GetMapping("/count/{tenantId}/{productId}")
    public R count(@PathVariable("tenantId") String tenantId,@PathVariable("productId") Long productId){
        return deviceService.selectDeviceCount(tenantId,productId);
    }

    @GetMapping("isOnline/{productKey}/{deviceKey}")
    public R checkOnline(@PathVariable(value = "productKey") String productKey,
                         @PathVariable(value = "deviceKey") String deviceKey){
        return deviceService.isOnline(productKey,deviceKey);
    }

}
