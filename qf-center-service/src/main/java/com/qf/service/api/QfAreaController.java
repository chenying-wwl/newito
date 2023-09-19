package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import com.qf.service.service.IQfAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行政区划表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/area")
@Tag(name = "AreaController", description = "行政区管理")
public class QfAreaController {

    @Autowired
    private IQfAreaService areaService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "行政区接口 - 查询所有",description = "根据条件查询所有行政区")
    public R<List> findAreaAll(@RequestBody(required = false) AreaDto dto) {
        List<QfArea> list = areaService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "行政区接口 - 根据id查询",description = "根据ID查询")
    public R<QfArea> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfArea area = areaService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "行政区接口 - 分页查询",description = "分页查询")
    R<Page<QfArea>> list(@RequestBody(required = false) AreaDto dto) {
        Page<QfArea> page = areaService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "行政区接口 - 新增",description = "新增")
    public R<QfArea> add(@RequestBody  QfArea area) {
        QfArea add = areaService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "行政区接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = areaService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "行政区接口 - 修改",description = "修改")
    public R<QfArea> update(@RequestBody QfArea area) {
        QfArea update = areaService.update(area);
        return R.ok(update);
    }
}
