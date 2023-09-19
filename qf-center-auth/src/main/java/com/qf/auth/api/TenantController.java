package com.qf.auth.api;

import com.qf.auth.service.TenantService;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("/isexist")

    public R isExist(String name){
        return tenantService.checkNameCanBeUsed(name);
    }

    @PostMapping("/regist")
    // name  pwd  phone  code companyName   companyCode  description
    public R regist(@RequestBody TenantDto tenantDto){
        return tenantService.tenantRegist(tenantDto);
    }

    @PostMapping("/login")
    public R login(@RequestBody TenantDto tenantDto){
        return tenantService.tenantAuth(tenantDto);
    }

}
