package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfProduct;
import com.qf.service.service.IQfProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/product")
@Tag(name = "QfProductController", description = "产品管理")
public class QfProductController {

    @Autowired
    private IQfProductService productService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "产品接口 - 查询所有",description = "根据条件查询所有产品")
    public R<List> findAreaAll(@RequestBody(required = false) ProductDto dto) {
        List<QfProduct> list = productService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "产品接口 - 根据id查询",description = "根据ID查询")
    public R<QfProduct> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfProduct area = productService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "产品接口 - 分页查询",description = "分页查询")
    R<Page<QfProduct>> list(@RequestBody(required = false) ProductDto dto) {
        Page<QfProduct> page = productService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "产品接口 - 新增",description = "新增")
    public R<QfProduct> add(@RequestBody  QfProduct area) {
        QfProduct add = productService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "产品接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = productService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "产品接口 - 修改",description = "修改")
    public R<QfProduct> update(@RequestBody QfProduct area) {
        QfProduct update = productService.update(area);
        return R.ok(update);
    }
}
