package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfModelTemplate;
import com.qf.service.service.IQfModelTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 物模型模板表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/model-template")
@Tag(name = "QfModelTemplateController", description = "物模型模板管理")
public class QfModelTemplateController {

    @Autowired
    private IQfModelTemplateService templateService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "物模型模板接口 - 查询所有",description = "根据条件查询所有物模型模板")
    public R<List> findAll(@RequestBody(required = false) ModelTemplateDto dto) {
        List<QfModelTemplate> list = templateService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "物模型模板接口 - 根据id查询",description = "根据ID查询")
    public R<QfModelTemplate> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfModelTemplate area = templateService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "物模型模板接口 - 分页查询",description = "分页查询")
    R<Page<QfModelTemplate>> list(@RequestBody(required = false) ModelTemplateDto dto) {
        Page<QfModelTemplate> page = templateService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "物模型模板接口 - 新增",description = "新增")
    public R<QfModelTemplate> add(@RequestBody  QfModelTemplate area) {
        QfModelTemplate add = templateService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "物模型模板接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = templateService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "物模型模板接口 - 修改",description = "修改")
    public R<QfModelTemplate> update(@RequestBody QfModelTemplate area) {
        QfModelTemplate update = templateService.update(area);
        return R.ok(update);
    }
}
