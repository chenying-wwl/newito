package com.qf.data.service.impl;

import com.qf.common.bean.R;
import com.qf.data.service.CheckCodeService;
import com.qf.data.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CheckCodeServiceImpl implements CheckCodeService {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public R saveByemail(String email, String code, long time) {
        try {
            redisUtil.setKey(email,code,time, TimeUnit.MINUTES);
            return R.ok(code,"验证码保存成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail();
    }

    @Override
    public String getByemail(String email) {
        try {
            String code = redisUtil.getKey(email, String.class);
            if(code != null){
                return code;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public R delByemail(String email) {
        try{
            redisUtil.deleteKey(email);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.fail();
    }


    @Override
    public R save(String phone, String code, long time)  {
        try {
            redisUtil.setKey(phone,code,time, TimeUnit.MINUTES);
            return R.ok(code,"验证码保存成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail();
    }

    @Override
    public R get(String phone) {
        try {
            String code = redisUtil.getKey(phone, String.class);
            if(code != null){
                return R.ok(code,"验证码查询成功");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail("手机号码不正确或验证码过期");
    }

    @Override
    public R del(String phone) {
        try{
            redisUtil.deleteKey(phone);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.fail();
    }

}
