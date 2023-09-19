package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.MenuDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfMenu;
import com.qf.service.service.IQfMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单管理 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/menu")
@Tag(name = "QfMenuController", description = "菜单管理")
public class QfMenuController {

    @Autowired
    private IQfMenuService menuService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "菜单接口 - 查询所有",description = "根据条件查询所有菜单")
    public R<List> findAreaAll(@RequestBody(required = false) MenuDto dto) {
        List<QfMenu> list = menuService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "菜单接口 - 根据id查询",description = "根据ID查询")
    public R<QfMenu> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfMenu area = menuService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "菜单接口 - 分页查询",description = "分页查询")
    R<Page<QfMenu>> list(@RequestBody(required = false) MenuDto dto) {
        Page<QfMenu> page = menuService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "菜单接口 - 新增",description = "新增")
    public R<QfMenu> add(@RequestBody  QfMenu area) {
        QfMenu add = menuService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "菜单接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = menuService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "菜单接口 - 修改",description = "修改")
    public R<QfMenu> update(@RequestBody QfMenu area) {
        QfMenu update = menuService.update(area);
        return R.ok(update);
    }
}
