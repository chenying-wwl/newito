package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfModelTemplate;
import java.util.List;
import java.util.Map;

/**
 * 物模型模板
 * @author 千锋健哥
 */
public interface ModelTemplateService {

    /**
     * 查询物模型模板下拉列表
     * @return
     */
    public R<Map<String, String>> findSelectAll();


    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(ModelTemplateDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfModelTemplate> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfModelTemplate>> list(ModelTemplateDto dto);

    /**
     * 新增
     */
    public R<QfModelTemplate> add(QfModelTemplate area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfModelTemplate> update(QfModelTemplate area);
}
