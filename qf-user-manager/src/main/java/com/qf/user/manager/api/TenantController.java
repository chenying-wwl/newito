package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import com.qf.user.manager.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/tenant")
@Tag(name = "TenantController", description = "租户管理")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "租户接口 - 查询所有",description = "根据条件查询所有租户")
    public R<List> findAreaAll(@RequestBody(required = false) TenantDto dto) {
        if (dto == null) {
            dto = new TenantDto();
        }
        R<List> list = tenantService.findAreaAll(dto);
        return list;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "租户接口 - 根据id查询",description = "根据ID查询")
    public R<QfTenant> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfTenant> tenant = tenantService.selectById(id);
        return tenant;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "租户接口 - 分页查询",description = "分页查询")
    public R<Page<QfTenant>> list(@RequestBody(required = false) TenantDto dto) {
        if (dto == null) {
            dto = new TenantDto();
        }
        R<Page<QfTenant>> page = tenantService.list(dto);
        return page;
    }

    /**
     * 审核通过
     * @param id
     * @return
     */
    @GetMapping("/checkyes/id/{id}")
    @Operation(summary = "租户接口 - 审核通过",description = "根据ID审核")
    public R<Boolean> checkYes(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> r = tenantService.checkTenantYes(id);
        return r;
    }

    /**
     * 审核不通过
     * @param id
     * @return
     */
    @GetMapping("/checkno/id/{id}")
    @Operation(summary = "租户接口 - 审核不通过",description = "根据ID审核")
    public R<Boolean> checkNo(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> r = tenantService.checkTenantNo(id);
        return r;
    }

    /**
     * 账户启用
     * @param id
     * @return
     */
    @GetMapping("/open/id/{id}")
    @Operation(summary = "租户接口 - 账户启用",description = "根据ID审核")
    public R<Boolean> open(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> r = tenantService.isEnableTenantYes(id);
        return r;
    }

    /**
     * 账户禁用
     * @param id
     * @return
     */
    @GetMapping("/close/id/{id}")
    @Operation(summary = "租户接口 - 账户禁用",description = "根据ID审核")
    public R<Boolean> close(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> r = tenantService.isEnableTenantNo(id);
        return r;
    }
}
