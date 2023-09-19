package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
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

}
