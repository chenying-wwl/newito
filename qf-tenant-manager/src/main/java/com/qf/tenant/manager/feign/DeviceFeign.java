package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "qf-center-service")
public interface DeviceFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/device/all")
    public R<List<QfDevice>> findAll(@RequestBody(required = false) DeviceDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/device/id/{id}")
    public R<QfDevice> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/device/list")
    R<Page<QfDevice>> list(@RequestBody(required = false) DeviceDto dto);

    /**
     * 修改
     */
    @PostMapping("/service/device/update")
    public R<QfDevice> update(@RequestBody QfDevice device);

    /**
     * 添加
     * @param device
     * @return
     */
    @PostMapping("/service/device/add")
    public R<QfDevice> add(@RequestBody QfDevice device);

    @GetMapping("/service/device/listByTenant/{tenantId}")
    R<List<DeviceDto>> lisByTenant(@PathVariable("tenantId") String tenantId);

    @GetMapping("/service/device/listByProduct/{productId}")
    R<List<DeviceDto>> lisByProduct(@PathVariable("productId") String productId);


    @PostMapping("/service/device/delete/{id}")
    public R<Boolean> delete(@PathVariable(value = "id") String id);

    @GetMapping("/service/device/detail/{id}")
    public R<DeviceDto> selectDetailById(@NotNull @PathVariable(value = "id") String id);

    @PostMapping("/service/device/count")
    public R<Integer> findAllCount(@RequestBody(required = false) DeviceDto dto);

}
