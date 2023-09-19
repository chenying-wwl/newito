package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户管理审核Feign接口
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface TenantFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/tenant/all")
    public R<List> findAreaAll(@RequestBody(required = false) TenantDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/tenant/id/{id}")
    public R<QfTenant> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/tenant/list")
    R<Page<QfTenant>> list(@RequestBody(required = false) TenantDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/tenant/add")
    public R<QfTenant> add(@RequestBody  QfTenant area);

    /**
     * 删除
     */
    @PostMapping("/service/tenant/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/tenant/update")
    public R<QfTenant> update(@RequestBody QfTenant area);
}
