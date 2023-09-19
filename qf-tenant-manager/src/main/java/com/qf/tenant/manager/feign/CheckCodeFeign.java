package com.qf.tenant.manager.feign;

import com.qf.common.bean.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "qf-center-data")
public interface CheckCodeFeign {

    @PostMapping("/data/checkCode/saveToRedis")
    public R saveToRedis(@RequestParam("phone") String phone,
                         @RequestParam("code") String code,
                         @RequestParam("time") long time);

    @GetMapping("/data/checkCode/getCode/{phone}")
    public R getCode(@PathVariable("phone") String phone);

    @DeleteMapping("/data/checkCode/delCode/{phone}")
    public R delCode(@PathVariable("phone") String phone);
}
