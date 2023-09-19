package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.IndustryDto;
import com.qf.common.model.QfIndustry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行业管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface IndustryFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/industry/all")
    public R<List> findAreaAll(@RequestBody(required = false) IndustryDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/industry/id/{id}")
    public R<QfIndustry> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/industry/list")
    R<Page<QfIndustry>> list(@RequestBody(required = false) IndustryDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/industry/add")
    public R<QfIndustry> add(@RequestBody  QfIndustry area);

    /**
     * 删除
     */
    @PostMapping("/service/industry/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/industry/update")
    public R<QfIndustry> update(@RequestBody QfIndustry area);
}
