package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.model.QfDeviceModel;
import com.qf.common.model.QfDeviceTopic;
import com.qf.service.service.IQfDeviceTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Topic主题表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/device-topic")
@Tag(name = "QfDeviceTopicController", description = "Topic主题管理")
public class QfDeviceTopicController {

    @Autowired
    private IQfDeviceTopicService topicService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "Topic主题接口 - 查询所有",description = "根据条件查询所有Topic主题")
    public R<List> findAll(@RequestBody(required = false) DeviceTopicDto dto) {
        List<QfDeviceTopic> list = topicService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Topic主题接口 - 根据id查询",description = "根据ID查询")
    public R<QfDeviceTopic> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfDeviceTopic deviceTopic = topicService.selectById(id);
        return R.ok(deviceTopic);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "Topic主题接口 - 分页查询",description = "分页查询")
    R<Page<QfDeviceTopic>> list(@RequestBody(required = false) DeviceTopicDto dto) {
        Page<QfDeviceTopic> page = topicService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "Topic主题接口 - 新增",description = "新增")
    public R<QfDeviceTopic> add(@RequestBody QfDeviceTopic device) {
        QfDeviceTopic add = topicService.add(device);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "Topic主题接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = topicService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "Topic主题接口 - 修改",description = "修改")
    public R<QfDeviceTopic> update(@RequestBody QfDeviceTopic device) {
        QfDeviceTopic update = topicService.update(device);
        return R.ok(update);
    }
}
