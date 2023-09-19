package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import com.qf.user.manager.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 行政区管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/area")
@Tag(name = "AreaController", description = "行政区管理")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 根据条件查询所有
     */
    @PostMapping("/all")
    @Operation(summary = "行政区接口 - 查询所有",description = "根据条件查询所有行政区")
    @PreAuthorize("hasAnyAuthority('sys:area:select')")
    public R<List> findAreaAll(@RequestBody(required = false) AreaDto dto) {
        if (dto == null) {
            dto = new AreaDto();
        }
        R<List> list = areaService.findAreaAll(dto);
        return list;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "行政区接口 - 根据id查询",description = "根据ID查询")
    public R<QfArea> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfArea> result = areaService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "行政区接口 - 分页查询",description = "分页查询")
    R<Page<QfArea>> list(@RequestBody(required = false) AreaDto dto) {
        if (dto == null) {
            dto = new AreaDto();
        }
        R<Page<QfArea>> result = areaService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "行政区接口 - 新增",description = "新增")
    public R<QfArea> add(@RequestBody  QfArea area) {
        area.setCreateTime(new Date());
        area.setUpdateTime(new Date());
        R<QfArea> result = areaService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "行政区接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = areaService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "行政区接口 - 修改",description = "修改")
    public R<QfArea> update(@RequestBody QfArea area){
        area.setUpdateTime(new Date());
        R<QfArea> result = areaService.update(area);
        return result;
    }
}
