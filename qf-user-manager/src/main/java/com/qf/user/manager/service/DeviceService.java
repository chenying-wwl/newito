package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备管理
 * @author 千锋健哥
 */
public interface DeviceService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(DeviceDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfDevice> selectById(String id);

    /**
     * 根据deviceKey查询
     * @param deviceKey
     * @return
     */
    public R<QfDevice> selectByDeviceKey(String deviceKey);

    /**
     * 分页查询
     */
    R<Page<QfDevice>> list(DeviceDto dto);


}
