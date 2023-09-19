package com.qf.auth.api;


import com.qf.auth.service.CheckCodeService;
import com.qf.common.bean.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth/checkcode")
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @PostMapping("/send")
    @Operation(summary = "租户注册",description = "发送短信验证码")
    public R send(String phone){
        R r = null;
        try {
            r = checkCodeService.send(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
