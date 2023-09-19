package com.qf.auth.service.impl;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.qf.auth.feign.CheckCodeFeign;
import com.qf.auth.feign.TenantFeign;
import com.qf.auth.service.TenantService;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import com.qf.common.utils.IdWorker;
import com.qf.common.utils.JwtUtil;
import com.qf.common.utils.QfUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantFeign tenantFeign;
    @Autowired
    private CheckCodeFeign checkCodeFeign;

    @Override
    public R checkNameCanBeUsed(String name) {
        TenantDto tenantDto = new TenantDto();
        tenantDto.setName(name);
        R<List<QfTenant>> r = tenantFeign.findTenantAll(tenantDto);
        if (r.getCode() == R.Code.OK.getCode() && r.getData().size() > 0) {
            return R.fail("账号名已经被占用，请更换账号名重试");
        } else {
            return R.ok("账号名可用");
        }
    }

    //对发送过来的邮箱码进行验证
    @Override
    public R regVerify(TenantDto tenantDto) {
        //1.短信验证码校验
        String code = checkCodeFeign.getCodeByemail(tenantDto.getPhone());

        if (code.equals(tenantDto.getCode())) {
            //验证码正确,将DTO转换为bean
            QfTenant qfTenant = new QfTenant();
            tenantDto.convertDtoToDo(qfTenant);
            //生成租户信息
            long id = new IdWorker().nextId();
            qfTenant.setId("" + id);
            //密码进行md5加密
            qfTenant.setPwd(QfUtil.md5(qfTenant.getPwd()));
            qfTenant.setEnable(1);
            //qfTenant.setCreateTime(new Date());
            //qfTenant.setUpdateTime(new Date());
            //qfTenant.setDeleted(0);
            qfTenant.setAccessKey("" + id);
            qfTenant.setAccessSecurit(UUID.randomUUID().toString().replace("-", ""));
            //qfTenant.setAuditStatus(0);
            //调用servcie服务存储租户信息
            R<QfTenant> r1 = tenantFeign.add(qfTenant);
            //注册完成，删除redis中的验证码
            checkCodeFeign.delCode(qfTenant.getPhone());
            return r1;
        } else {
            //验证码错误
            return R.fail("验证码错误");
        }
    }




    //手机验证码验证
    @Override
    public R tenantRegist(TenantDto tenantDto) {
        //验证码正确,将DTO转换为bean
        QfTenant qfTenant = new QfTenant();
        tenantDto.convertDtoToDo(qfTenant);
        //生成租户信息
        long id = new IdWorker().nextId();
        qfTenant.setId(""+id);
        //密码进行md5加密
        qfTenant.setPwd(QfUtil.md5(qfTenant.getPwd()));
        qfTenant.setEnable(1);
        //qfTenant.setCreateTime(new Date());
        //qfTenant.setUpdateTime(new Date());
        //qfTenant.setDeleted(0);
        qfTenant.setAccessKey(""+id);
        qfTenant.setAccessSecurit(UUID.randomUUID().toString().replace("-",""));
        //qfTenant.setAuditStatus(0);
        //调用servcie服务存储租户信息
        R<QfTenant> r1 = tenantFeign.add(qfTenant);
        //注册完成，删除redis中的验证码
        checkCodeFeign.delCode(qfTenant.getPhone());
        return r1;
        //1.短信验证码校验
        /*
        R r = checkCodeFeign.getCode(tenantDto.getPhone());
        if(r.isOk()){
            if( r.getData().equals(tenantDto.getCode())){
                //验证码正确,将DTO转换为bean
                QfTenant qfTenant = new QfTenant();
                tenantDto.convertDtoToDo(qfTenant);
                //生成租户信息
                long id = new IdWorker().nextId();
                qfTenant.setId(""+id);
                //密码进行md5加密
                qfTenant.setPwd(QfUtil.md5(qfTenant.getPwd()));
                qfTenant.setEnable(1);
                //qfTenant.setCreateTime(new Date());
                //qfTenant.setUpdateTime(new Date());
                //qfTenant.setDeleted(0);
                qfTenant.setAccessKey(""+id);
                qfTenant.setAccessSecurit(UUID.randomUUID().toString().replace("-",""));
                //qfTenant.setAuditStatus(0);
                //调用servcie服务存储租户信息
                R<QfTenant> r1 = tenantFeign.add(qfTenant);
                //注册完成，删除redis中的验证码
                checkCodeFeign.delCode(qfTenant.getPhone());
                return r1;
            }else{
                //验证码错误
                return R.fail("验证码错误");
            }
        }else{
            return r;
        }*/
    }

    @Override
    public R tenantAuth(TenantDto tenantDto) {
        TenantDto tenantDto1 = new TenantDto();
        tenantDto1.setName(tenantDto.getName());
        R<List<QfTenant>> r = tenantFeign.findTenantAll(tenantDto1);
        if(r.getCode() == R.Code.OK.getCode() && r.getData().size()>0){
            QfTenant qfTenant = (QfTenant) r.getData().get(0);
            //验证密码
            String pwd = QfUtil.md5(tenantDto.getPwd());
            if(qfTenant.getPwd().equals(pwd)){
                //认证成功之token待生成
                String token = JwtUtil.createJWT(UUID.randomUUID().toString(), qfTenant.getId(), null);
                return R.ok(token);
            }else{
                return R.fail("登录密码错误！");
            }
        }else{
            return R.fail("登录账号不存在！");
        }
    }

}
