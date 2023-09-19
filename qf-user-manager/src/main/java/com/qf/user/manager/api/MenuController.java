package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.dto.MenuDto;
import com.qf.common.model.QfMenu;
import com.qf.user.manager.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/menu")
@Tag(name = "MenuController", description = "菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "菜单接口 - 查询所有",description = "根据条件查询所有菜单")
    public R<List> findAreaAll(@RequestBody(required = false) MenuDto dto) {
        if (dto == null) {
            dto = new MenuDto();
        }
        R<List> result = menuService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "菜单接口 - 根据id查询",description = "根据ID查询")
    public R<QfMenu> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfMenu> result = menuService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "菜单接口 - 分页查询",description = "分页查询")
    R<Page<QfMenu>> list(@RequestBody(required = false) MenuDto dto) {
        if (dto == null) {
            dto = new MenuDto();
        }
        R<Page<QfMenu>> result = menuService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "菜单接口 - 新增",description = "新增")
    public R<QfMenu> add(@RequestBody  QfMenu area) {
        R<QfMenu> result = menuService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "菜单接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = menuService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "菜单接口 - 修改",description = "修改")
    public R<QfMenu> update(@RequestBody QfMenu area) {
        R<QfMenu> result = menuService.update(area);
        return result;
    }
}
