package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行政区管理Feign接口
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface AreaFeign {

    /**
     * 根据条件查询所有
     */
    @PostMapping(value = "/service/area/all")
    public R<List> findAreaAll(@RequestBody(required = false) AreaDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/area/id/{id}")
    public R<QfArea> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/area/list")
    R<Page<QfArea>> list(@RequestBody(required = false) AreaDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/area/add")
    public R<QfArea> add(@RequestBody  QfArea area);

    /**
     * 删除
     */
    @PostMapping("/service/area/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/area/update")
    public R<QfArea> update(@RequestBody QfArea area);
}
