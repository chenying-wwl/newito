package com.qf.data.api;

import com.qf.common.bean.R;
import com.qf.data.service.CheckCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data/checkCode")
@Tag(name = "CheckCodeController", description = "短信验证码服务")
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @PostMapping("/saveToRedisByemail")
    public R saveToRedisByemail(String email, String code, long time){
        return  checkCodeService.saveByemail(email, code, time);
    }

    @GetMapping("/getCodeByemail/{email}")
    public String getCodeByemail(@PathVariable("email") String email){
        return checkCodeService.getByemail(email);
    }
    @DeleteMapping("/delCodeByemail/{email}")
    public R delCodeByemail(@PathVariable("email") String email){
        return checkCodeService.del(email);
    }



    @PostMapping("/saveToRedis")
    public R saveToRedis(String phone, String code, long time){
        return  checkCodeService.save(phone, code, time);
    }

    @GetMapping("/getCode/{phone}")
    public R getCode(@PathVariable("phone") String phone){
        return checkCodeService.get(phone);
    }


    @DeleteMapping("/delCode/{phone}")
    public R delCode(@PathVariable("phone") String phone){
        return checkCodeService.del(phone);
    }



}
