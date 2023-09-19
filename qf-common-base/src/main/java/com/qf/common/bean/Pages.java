package com.qf.common.bean;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础查询类，其中包括分页以及排序
 *
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "基础查询类，其中包括分页以及排序")
public class Pages implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页")
    private long current = 1;

    @Schema(description = "每页条数")
    private long size = 20;

    @Schema(description = "起始时间")
    private long startTime;

    @Schema(description = "结束时间")
    private long endTime;

    @Schema(description = "排序")
    private List<OrderItem> orders = new ArrayList<>(4);

    public <T> Page<T> convert() {
        Page<T> page = new Page<>();
        BeanUtils.copyProperties(this, page);

        boolean createTimeOrder = false;
        for (OrderItem order : page.orders()) {
            if (StrUtil.isNotEmpty(order.getColumn()) && order.getColumn().equals("create_time")) {
                createTimeOrder = true;
            }
        }
        if (!createTimeOrder) {
            page.orders().add(OrderItem.desc("create_time"));
        }
        return page;
    }

}
