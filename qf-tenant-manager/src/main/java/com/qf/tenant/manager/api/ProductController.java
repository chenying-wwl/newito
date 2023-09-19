package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import com.qf.tenant.manager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tenant/product")
@Tag(name = "ProductController", description = "租户系统产品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据条件查询所有
     * @return
     */
    @GetMapping("/all")
    @Operation(summary = "产品接口 - 查询所有",description = "根据条件查询所有产品")
    public R<List<QfProduct>> findAreaAll() {
        R<List<QfProduct>> result = productService.findAreaAll();
        return result;
    }

    // name 产品备注名称
    // tenantId 所属的租户
    // areaCode 所属行政区编码
    @PostMapping("/add")
    public R add(@RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @GetMapping("/list")
    public R listByTenantId(ProductDto productDto){
        return productService.listByTenantId(productDto);
    }

    @GetMapping("/list/{tenantId}")
    public R listByTenantId(@PathVariable("tenantId") String tenantId){
        return productService.listByTenantId(tenantId);
    }




    @GetMapping("/get/{productKey}")
    public R getByProductKey(String productKey){
        return productService.getByProductKey(productKey);
    }

}
