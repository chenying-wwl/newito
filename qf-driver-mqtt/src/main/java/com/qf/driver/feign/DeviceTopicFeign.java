package com.qf.driver.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.model.QfDeviceTopic;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备Topic Feign接口
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface DeviceTopicFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/device-topic/all")
    public R<List> findAreaAll(@RequestBody(required = false) DeviceTopicDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/device-topic/id/{id}")
    public R<QfDeviceTopic> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/device-topic/list")
    R<Page<QfDeviceTopic>> list(@RequestBody(required = false) DeviceTopicDto dto);
}
