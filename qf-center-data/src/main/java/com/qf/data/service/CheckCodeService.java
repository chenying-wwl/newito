package com.qf.data.service;

import com.qf.common.bean.R;

public interface CheckCodeService {
    /**
     * 存储邮箱及生成的验证码到redis
     * @param email
     * @param code
     * @param time
     * @throws Exception
     */
    public R saveByemail(String email,String code,long time);

    /**
     * 根据邮箱从redis查询对应的验证码
     * @param email
     * @return
     * @throws Exception
     */
    public String getByemail(String email);
    /**
     * 根据手机号码从redis删除对应的验证码
     * @param email
     * @return
     * @throws Exception
     */
    public R delByemail(String email);
    /**
     * 存储手机号码及生成的验证码到redis
     * @param phone
     * @param code
     * @param time
     * @throws Exception
     */
    public R save(String phone,String code,long time);

    /**
     * 根据手机号码从redis查询对应的验证码
     * @param phone
     * @return
     * @throws Exception
     */
    public R get(String phone);

    /**
     * 根据手机号码从redis删除对应的验证码
     * @param phone
     * @return
     * @throws Exception
     */
    public R del(String phone);

}
