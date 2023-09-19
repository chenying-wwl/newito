package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.GroupDto;
import com.qf.common.model.QfGroup;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "qf-center-service")
public interface GroupFeign {

    @PostMapping("/service/group/add")
    public R<QfGroup> add(@RequestBody QfGroup qfGroup);

    @PostMapping("/service/group/all")
    public R<List> findAll(@RequestBody(required = false) GroupDto dto);

    @PostMapping("/service/group/list")
    R<Page<QfGroup>> list(@RequestBody(required = false) GroupDto dto);

}
