package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.SysDictTypeDto;
import com.qf.common.mapper.QfSysDictTypeMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfSysDictType;
import com.qf.service.service.IQfSysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 字典类型 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/sys-dict-type")
@Tag(name = "QfSysDictTypeController", description = "字典类型管理")
public class QfSysDictTypeController {

    @Autowired
    private IQfSysDictTypeService typeService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "字典类型接口 - 查询所有",description = "根据条件查询所有字典类型")
    public R<List> findAreaAll(@RequestBody(required = false) SysDictTypeDto dto) {
        List<QfSysDictType> list = typeService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "字典类型接口 - 根据id查询",description = "根据ID查询")
    public R<QfSysDictType> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfSysDictType area = typeService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "字典类型接口 - 分页查询",description = "分页查询")
    R<Page<QfSysDictType>> list(@RequestBody(required = false) SysDictTypeDto dto) {
        Page<QfSysDictType> page = typeService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "字典类型接口 - 新增",description = "新增")
    public R<QfSysDictType> add(@RequestBody  QfSysDictType area) {
        QfSysDictType add = typeService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "字典类型接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = typeService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "字典类型接口 - 修改",description = "修改")
    public R<QfSysDictType> update(@RequestBody QfSysDictType area) {
        QfSysDictType update = typeService.update(area);
        return R.ok(update);
    }
}
