package com.qf.auth.service;


import com.qf.common.bean.R;

public interface CheckCodeService {

    public R send(String phone) throws Exception;

    //采用邮箱验证
    public void sendByEmail(String email) throws  Exception;
}
