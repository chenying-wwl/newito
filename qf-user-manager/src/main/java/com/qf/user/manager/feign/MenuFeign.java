package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MenuDto;
import com.qf.common.model.QfMenu;
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
public interface MenuFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/menu/all")
    public R<List> findAreaAll(@RequestBody(required = false) MenuDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/menu/id/{id}")
    public R<QfMenu> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/menu/list")
    R<Page<QfMenu>> list(@RequestBody(required = false) MenuDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/menu/add")
    public R<QfMenu> add(@RequestBody  QfMenu area);

    /**
     * 删除
     */
    @PostMapping("/service/menu/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/menu/update")
    public R<QfMenu> update(@RequestBody QfMenu area);
}
