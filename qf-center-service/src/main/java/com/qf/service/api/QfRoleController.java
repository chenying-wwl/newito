package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.RoleDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfRole;
import com.qf.service.service.IQfRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色管理 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/role")
@Tag(name = "QfRoleController", description = "角色管理")
public class QfRoleController {

    @Autowired
    private IQfRoleService roleService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "角色接口 - 查询所有",description = "根据条件查询所有角色")
    public R<List> findAreaAll(@RequestBody(required = false) RoleDto dto) {
        List<QfRole> list = roleService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "角色接口 - 根据id查询",description = "根据ID查询")
    public R<QfRole> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfRole area = roleService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "角色接口 - 分页查询",description = "分页查询")
    R<Page<QfRole>> list(@RequestBody(required = false) RoleDto dto) {
        Page<QfRole> page = roleService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "角色接口 - 新增",description = "新增")
    public R<QfRole> add(@RequestBody  QfRole area) {
        QfRole add = roleService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "角色接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = roleService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "角色接口 - 修改",description = "修改")
    public R<QfRole> update(@RequestBody QfRole area) {
        QfRole update = roleService.update(area);
        return R.ok(update);
    }
}
