package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 租户管理
 * @author 千锋健哥
 */
public interface TenantService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(TenantDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfTenant> selectById(String id);

    /**
     * 分页查询
     */
    public R<Page<QfTenant>> list(TenantDto dto);

    /**
     * 审核通过
     * @param id
     * @return
     */
    public R<Boolean> checkTenantYes(String id);

    /**
     * 审核不通过
     * @param id
     * @return
     */
    public R<Boolean> checkTenantNo(String id);

    /**
     * 租户启用
     * @param id
     * @return
     */
    public R<Boolean> isEnableTenantYes(String id);

    /**
     * 租户禁用
     * @param id
     * @return
     */
    public R<Boolean> isEnableTenantNo(String id);
}
