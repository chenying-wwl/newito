package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备类型管理
 * @author 千锋健哥
 */
public interface DeviceTypeService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(DeviceTypeDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfDeviceType> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfDeviceType>> list(DeviceTypeDto dto);

    /**
     * 新增
     */
    public R<QfDeviceType> add(QfDeviceType deviceType);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfDeviceType> update(QfDeviceType deviceType);
}
