package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.IndustryDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import com.qf.user.manager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/product")
@Tag(name = "ProductController", description = "产品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "产品接口 - 查询所有",description = "根据条件查询所有产品")
    public R<List> findAreaAll(@RequestBody(required = false) ProductDto dto) {
        if (dto == null) {
            dto = new ProductDto();
        }
        R<List> result = productService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "产品接口 - 根据id查询",description = "根据ID查询")
    public R<QfProduct> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfProduct> result = productService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "产品接口 - 分页查询",description = "分页查询")
    R<Page<QfProduct>> list(@RequestBody(required = false) ProductDto dto) {
        if (dto == null) {
            dto = new ProductDto();
        }
        R<Page<QfProduct>> result = productService.list(dto);
        return result;
    }
}
