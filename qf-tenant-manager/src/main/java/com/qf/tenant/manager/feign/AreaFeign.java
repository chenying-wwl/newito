package com.qf.tenant.manager.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 行政区
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface AreaFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/area/all")
    @Operation(summary = "行政区接口 - 查询所有",description = "根据条件查询所有行政区")
    public R<List> findAreaAll(@RequestBody(required = false) AreaDto dto);
}
