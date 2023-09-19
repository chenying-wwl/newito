package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.Pages;
import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.user.manager.feign.CmdFeign;
import com.qf.user.manager.feign.DeviceCmdFeign;
import com.qf.user.manager.service.CmdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * 设备指令接口
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/cmd")
@Tag(name = "CmdController", description = "平台指令发送")
public class CmdController {

    @Autowired
    private CmdFeign cmdFeign;

    @Autowired
    private CmdService cmdService;

    /**
     * 向设备发送指令
     * @param dto
     * @return
     */
    @PostMapping("/send")
    @Operation(summary = "指令发送",description = "发送指令到Mqtt服务器")
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto) {
        if (StringUtils.isEmpty(dto.getCmdName())
                || StringUtils.isEmpty(dto.getDeviceKey())
                || StringUtils.isEmpty(dto.getValue())) {
            return R.fail("设备Key, 指令名称, 指令值不允许为空!");
        }
        R<String> result = cmdFeign.cmdSend(dto);
        return result;
    }

    @PostMapping("/list")
    @Operation(summary = "设备指令操作日志接口 - 分页查询",description = "根据条件查询设备指令操作日志")
    public R<Page<DeviceCmd>> list(@RequestBody DeviceCmdDto deviceCmdDto) {
        if (deviceCmdDto == null) {
            return R.fail("查询条件不可以为空");
        }
        if (StringUtils.isEmpty(deviceCmdDto.getDeviceKey())) {
            return R.fail("设备Key不可以为空");
        }
        //如果时间为空, 设置默认查询时间范围
        if (deviceCmdDto.getPage() == null) {
            deviceCmdDto.setPage(new Pages());
        }
        if (deviceCmdDto.getPage().getStartTime() == 0
                || deviceCmdDto.getPage().getEndTime() == 0) {
            //开始时间
            long endTime = System.currentTimeMillis();
            Calendar calBegin = Calendar.getInstance();
            calBegin.add(Calendar.DAY_OF_MONTH, -1);
            //结束时间
            long startTime = calBegin.getTimeInMillis();
            deviceCmdDto.getPage().setStartTime(startTime);
            deviceCmdDto.getPage().setEndTime(endTime);
        }
        R<Page<DeviceCmd>> result = cmdService.list(deviceCmdDto);
        return  result;

    }
}
