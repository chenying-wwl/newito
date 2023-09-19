package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfModelTemplate;
import com.qf.user.manager.service.ModelTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 物模型模板管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/model-template")
@Tag(name = "ModelTemplateController", description = "物模型模板管理")
public class ModelTemplateController {

    @Autowired
    private ModelTemplateService templateService;

    @PostMapping("/select")
    @Operation(summary = "物模型模板接口 - 查询下拉列表",description = "物模型模板下拉列表")
    public R<Map<String, String>> findSelectAll() {
        R<Map<String, String>> result = templateService.findSelectAll();
        return result;
    }

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "物模型模板接口 - 查询所有",description = "根据条件查询所有物模型模板")
    public R<List> findAreaAll(@RequestBody(required = false) ModelTemplateDto dto) {
        if (dto == null) {
            dto = new ModelTemplateDto();
        }
        R<List> result = templateService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "物模型模板接口 - 根据id查询",description = "根据ID查询")
    public R<QfModelTemplate> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfModelTemplate> result = templateService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "物模型模板接口 - 分页查询",description = "分页查询")
    R<Page<QfModelTemplate>> list(@RequestBody(required = false) ModelTemplateDto dto) {
        if (dto == null) {
            dto = new ModelTemplateDto();
        }
        R<Page<QfModelTemplate>> result = templateService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "物模型模板接口 - 新增",description = "新增")
    public R<QfModelTemplate> add(@RequestBody  QfModelTemplate area) {
        R<QfModelTemplate> result = templateService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "物模型模板接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = templateService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "物模型模板接口 - 修改",description = "修改")
    public R<QfModelTemplate> update(@RequestBody QfModelTemplate area) {
        R<QfModelTemplate> result = templateService.update(area);
        return result;
    }
}
