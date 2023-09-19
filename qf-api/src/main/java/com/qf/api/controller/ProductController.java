package com.qf.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.api.service.ProductService;
import com.qf.api.service.TenantService;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import com.qf.common.model.QfTenant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Result;
import java.util.List;

/**
 * 第三方业务系统产品管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/api/product")
@Tag(name = "ProductController", description = "产品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "产品接口 - 查询所有",description = "根据条件查询所有产品")
    public R<List> findAreaAll(@RequestBody(required = false) ProductDto dto) {

        //1. 从请求头中获取access_key
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return R.fail("缺少租户, 无权限!");
        }

        //2. 判断access是否存在
        QfTenant tenant = tenantService.findTenantByAccessKey(token);
        if (tenant == null) {
            return R.fail("缺少租户, 无权限!");
        }

        //3. 查询产品条件中加入租户ID条件
        if (dto == null) {
            dto = new ProductDto();
            dto.setTenantId(tenant.getId());
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
        //1. 从请求头中获取access_key
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return R.fail("缺少租户, 无权限!");
        }

        //2. 判断access是否存在
        QfTenant tenant = tenantService.findTenantByAccessKey(token);
        if (tenant == null) {
            return R.fail("缺少租户, 无权限!");
        }

        //3. 查询产品条件中加入租户ID条件
        if (dto == null) {
            dto = new ProductDto();
            dto.setTenantId(tenant.getId());
        }

        R<Page<QfProduct>> result = productService.list(dto);
        return result;
    }
}
