package com.qf.tenant.manager.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.dto.ModelTemplateDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "qf-center-service")
public interface ModelTemplateFeign {

    @PostMapping("/service/model-template/all")
    public R<List> findAll(@RequestBody(required = false) ModelTemplateDto dto);
}
