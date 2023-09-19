package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfModelTemplate;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 物模型模板管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface ModelTemplateFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/model-template/all")
    public R<List> findAreaAll(@RequestBody(required = false) ModelTemplateDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/model-template/id/{id}")
    public R<QfModelTemplate> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/model-template/list")
    R<Page<QfModelTemplate>> list(@RequestBody(required = false) ModelTemplateDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/model-template/add")
    public R<QfModelTemplate> add(@RequestBody QfModelTemplate area);

    /**
     * 删除
     */
    @PostMapping("/service/model-template/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/model-template/update")
    public R<QfModelTemplate> update(@RequestBody QfModelTemplate area);
}
