package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.SysDictDataDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfSysDictData;
import com.qf.service.service.IQfSysDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统字典数据 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/sys-dict-data")
@Tag(name = "QfSysDictDataController", description = "系统字典管理")
public class QfSysDictDataController {

    @Autowired
    private IQfSysDictDataService dataService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "字典数据接口 - 查询所有",description = "根据条件查询所有字典数据")
    public R<List> findAreaAll(@RequestBody(required = false) SysDictDataDto dto) {
        List<QfSysDictData> list = dataService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "字典数据接口 - 根据id查询",description = "根据ID查询")
    public R<QfSysDictData> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfSysDictData area = dataService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "字典数据接口 - 分页查询",description = "分页查询")
    R<Page<QfSysDictData>> list(@RequestBody(required = false) SysDictDataDto dto) {
        Page<QfSysDictData> page = dataService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "字典数据接口 - 新增",description = "新增")
    public R<QfSysDictData> add(@RequestBody  QfSysDictData area) {
        QfSysDictData add = dataService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "字典数据接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = dataService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "字典数据接口 - 修改",description = "修改")
    public R<QfSysDictData> update(@RequestBody QfSysDictData area) {
        QfSysDictData update = dataService.update(area);
        return R.ok(update);
    }
}
