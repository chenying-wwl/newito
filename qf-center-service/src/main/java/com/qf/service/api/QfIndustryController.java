package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.GroupDto;
import com.qf.common.dto.IndustryDto;
import com.qf.common.model.QfGroup;
import com.qf.common.model.QfIndustry;
import com.qf.service.service.IQfIndustryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行业表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/industry")
@Tag(name = "QfIndustryController", description = "行业管理")
public class QfIndustryController {

    @Autowired
    private IQfIndustryService industryService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "行业接口 - 查询所有",description = "根据条件查询所有行业")
    public R<List> findAreaAll(@RequestBody(required = false) IndustryDto dto) {
        List<QfIndustry> list = industryService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "行业接口 - 根据id查询",description = "根据ID查询")
    public R<QfIndustry> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfIndustry area = industryService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "行业接口 - 分页查询",description = "分页查询")
    R<Page<QfIndustry>> list(@RequestBody(required = false) IndustryDto dto) {
        Page<QfIndustry> page = industryService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "行业接口 - 新增",description = "新增")
    public R<QfIndustry> add(@RequestBody  QfIndustry area) {
        QfIndustry add = industryService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "行业接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = industryService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "行业接口 - 修改",description = "修改")
    public R<QfIndustry> update(@RequestBody QfIndustry area) {
        QfIndustry update = industryService.update(area);
        return R.ok(update);
    }
}
