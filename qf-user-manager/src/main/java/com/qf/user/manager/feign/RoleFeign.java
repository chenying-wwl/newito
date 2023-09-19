package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.RoleDto;
import com.qf.common.model.QfRole;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface RoleFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/role/all")
    public R<List> findAreaAll(@RequestBody(required = false) RoleDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/role/id/{id}")
    public R<QfRole> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/role/list")
    R<Page<QfRole>> list(@RequestBody(required = false) RoleDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/role/add")
    public R<QfRole> add(@RequestBody  QfRole area);

    /**
     * 删除
     */
    @PostMapping("/service/role/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/role/update")
    public R<QfRole> update(@RequestBody QfRole area);
}
