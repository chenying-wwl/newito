package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备类型管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface DeviceTypeFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/device-type/all")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceTypeDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/device-type/id/{id}")
    public R<QfDeviceType> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/device-type/list")
    R<Page<QfDeviceType>> list(@RequestBody(required = false) DeviceTypeDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/device-type/add")
    public R<QfDeviceType> add(@RequestBody  QfDeviceType deviceType);

    /**
     * 删除
     */
    @PostMapping("/service/device-type/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/device-type/update")
    public R<QfDeviceType> update(@RequestBody QfDeviceType deviceType);
}
