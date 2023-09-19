package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.IndustryDto;
import com.qf.common.model.QfIndustry;
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
public interface IndustryService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(IndustryDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfIndustry> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfIndustry>> list(IndustryDto dto);

    /**
     * 新增
     */
    public R<QfIndustry> add(QfIndustry area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfIndustry> update(QfIndustry area);
}
