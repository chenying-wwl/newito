package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.GroupDto;
import com.qf.common.model.QfGroup;
import com.qf.tenant.manager.service.DeviceService;
import com.qf.tenant.manager.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tenant/group")
@Tag(name = "GroupController", description = "租户系统设备管理")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public R add(@RequestBody QfGroup qfGroup){
        return groupService.saveGroup(qfGroup);
    }

    @GetMapping("/list")
    public R list(GroupDto groupDto){
        return groupService.listGroup(groupDto);
    }

}
