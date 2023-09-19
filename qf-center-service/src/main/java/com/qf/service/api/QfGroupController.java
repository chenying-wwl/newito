package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.GroupDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfGroup;
import com.qf.service.service.IQfGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户设备分组表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/group")
@Tag(name = "QfGroupController", description = "租户设备分组管理")
public class QfGroupController {

    @Autowired
    private IQfGroupService groupService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "租户设备分组接口 - 查询所有",description = "根据条件查询所有租户设备分组")
    public R<List> findAll(@RequestBody(required = false) GroupDto dto) {
        List<QfGroup> list = groupService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "租户设备分组接口 - 根据id查询",description = "根据ID查询")
    public R<QfGroup> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfGroup area = groupService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "租户设备分组接口 - 分页查询",description = "分页查询")
    R<Page<QfGroup>> list(@RequestBody(required = false) GroupDto dto) {
        Page<QfGroup> page = groupService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "租户设备分组接口 - 新增",description = "新增")
    public R<QfGroup> add(@RequestBody  QfGroup area) {
        QfGroup add = groupService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "租户设备分组接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = groupService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "租户设备分组接口 - 修改",description = "修改")
    public R<QfGroup> update(@RequestBody QfGroup area) {
        QfGroup update = groupService.update(area);
        return R.ok(update);
    }
}
