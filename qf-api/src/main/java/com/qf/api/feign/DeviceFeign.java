package com.qf.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface DeviceFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/device/all")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceDto dto);

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
}
