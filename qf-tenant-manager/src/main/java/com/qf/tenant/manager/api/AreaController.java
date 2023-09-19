package com.qf.tenant.manager.api;

import com.qf.common.bean.R;
import com.qf.common.model.QfArea;
import com.qf.tenant.manager.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 行政区
 * @author 千锋健哥
 */
@RestController
@CrossOrigin
@RequestMapping("/tenant/area")
@Tag(name = "AreaController", description = "行政区")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 根据父id查询所属行政区列表
     * @param parentId
     * @return
     */
    @GetMapping("/parentId/{parentId}")
    @Operation(summary = "行政区接口 - 根据父id查询",description = "根据父id查询所属行政区列表")
    public R<List> findByParentId(@PathVariable("parentId") Integer parentId) {
        R<List> result = areaService.findByParentId(parentId);
        return result;
    }
}
