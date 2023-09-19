package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfTenant;
import com.qf.service.service.IQfTenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/tenant")
@Tag(name = "QfTenantController", description = "租户管理")
public class QfTenantController {

    @Autowired
    private IQfTenantService tenantService;



    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "租户接口 - 查询所有",description = "根据条件查询所有租户")
    public R<List<QfTenant>> findTenantAll(@RequestBody(required = false) TenantDto dto) {
        List<QfTenant> list = tenantService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "租户接口 - 根据id查询",description = "根据ID查询")
    public R<QfTenant> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfTenant area = tenantService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "租户接口 - 分页查询",description = "分页查询")
    R<Page<QfTenant>> list(@RequestBody(required = false) TenantDto dto) {
        Page<QfTenant> page = tenantService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "租户接口 - 新增",description = "新增")
    public R<QfTenant> add(@RequestBody  QfTenant qfTenant) {
        QfTenant add = tenantService.add(qfTenant);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "租户接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = tenantService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "租户接口 - 修改",description = "修改")
    public R<QfTenant> update(@RequestBody QfTenant area) {
        QfTenant update = tenantService.update(area);
        return R.ok(update);
    }
}
