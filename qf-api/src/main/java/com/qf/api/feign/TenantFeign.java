package com.qf.api.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "qf-center-service")
public interface TenantFeign {

    @PostMapping("/service/tenant/all")
    public R<List<QfTenant>> findTenantAll(@RequestBody(required = false) TenantDto dto);

    @PostMapping("/service/tenant/add")
    public R<QfTenant> add(@RequestBody QfTenant qfTenant);

}
