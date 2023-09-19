package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface ProductFeign {

    @PostMapping("/service/product/add")
    public R<QfProduct> add(@RequestBody  ProductDto productDto);

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/product/all")
    public R<List<QfProduct>> findAll(@RequestBody(required = false) ProductDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/product/id/{id}")
    public R<QfProduct> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/product/list")
    R<Page<QfProduct>> list(@RequestBody(required = false) ProductDto dto);
}
