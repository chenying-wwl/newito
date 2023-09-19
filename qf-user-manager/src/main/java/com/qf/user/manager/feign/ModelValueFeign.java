package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 设备上报数据
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-data")
public interface ModelValueFeign {

    /**
     * 分页查询设备上报的数据, 数据存储在mongodb中
     * @param deviceModelValueDto
     * @return
     */
    @PostMapping("/data/deviceModelValue/list")
    public R<Page<DeviceModelValue>> list(@RequestBody DeviceModelValueDto deviceModelValueDto);
}
