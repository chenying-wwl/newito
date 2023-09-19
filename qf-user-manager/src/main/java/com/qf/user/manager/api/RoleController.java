package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MenuDto;
import com.qf.common.dto.RoleDto;
import com.qf.common.model.QfRole;
import com.qf.user.manager.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/role")
@Tag(name = "RoleController", description = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "角色接口 - 查询所有",description = "根据条件查询所有角色")
    public R<List> findAreaAll(@RequestBody(required = false) RoleDto dto) {
        if (dto == null) {
            dto = new RoleDto();
        }
        R<List> result = roleService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "角色接口 - 根据id查询",description = "根据ID查询")
    public R<QfRole> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfRole> result = roleService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "角色接口 - 分页查询",description = "分页查询")
    R<Page<QfRole>> list(@RequestBody(required = false) RoleDto dto) {
        if (dto == null) {
            dto = new RoleDto();
        }
        R<Page<QfRole>> result = roleService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "角色接口 - 新增",description = "新增")
    public R<QfRole> add(@RequestBody  QfRole area) {
        R<QfRole> result = roleService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "角色接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = roleService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "角色接口 - 修改",description = "修改")
    public R<QfRole> update(@RequestBody QfRole area) {
        R<QfRole> result = roleService.update(area);
        return result;
    }
}
