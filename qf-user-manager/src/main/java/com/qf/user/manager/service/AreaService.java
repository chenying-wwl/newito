package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 千锋健哥
 */
public interface AreaService {

    /**
     * 根据条件查询所有
     */
    public R<List> findAreaAll(AreaDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfArea> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfArea>> list(AreaDto dto);

    /**
     * 新增
     */
    public R<QfArea> add(QfArea area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfArea> update(QfArea area);
}
