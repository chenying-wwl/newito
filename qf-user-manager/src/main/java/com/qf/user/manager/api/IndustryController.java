package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.dto.IndustryDto;
import com.qf.common.model.QfIndustry;
import com.qf.user.manager.service.IndustryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行业管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/industry")
@Tag(name = "IndustryController", description = "行业管理")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "行业接口 - 查询所有",description = "根据条件查询所有行业")
    public R<List> findAreaAll(@RequestBody(required = false) IndustryDto dto) {
        if (dto == null) {
            dto = new IndustryDto();
        }
        R<List> result = industryService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "行业接口 - 根据id查询",description = "根据ID查询")
    public R<QfIndustry> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfIndustry> result = industryService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "行业接口 - 分页查询",description = "分页查询")
    public R<Page<QfIndustry>> list(@RequestBody(required = false) IndustryDto dto) {
        if (dto == null) {
            dto = new IndustryDto();
        }
        R<Page<QfIndustry>> result = industryService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "行业接口 - 新增",description = "新增")
    public R<QfIndustry> add(@RequestBody  QfIndustry area) {
        R<QfIndustry> result = industryService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "行业接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = industryService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "行业接口 - 修改",description = "修改")
    public R<QfIndustry> update(@RequestBody QfIndustry area) {
        R<QfIndustry> result = industryService.update(area);
        return result;
    }
}
