package com.qf.auth.service.impl;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.qf.auth.feign.CheckCodeFeign;
import com.qf.auth.service.CheckCodeService;
import com.qf.common.bean.R;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CheckCodeServiceImpl implements CheckCodeService {
    @Autowired
    public JavaMailSender emailSender;

    //获取配置文件中的邮箱的值
    @Value("${spring.mail.username}")
    String from;
    @Autowired
    private CheckCodeFeign checkCodeFeign;


    //进行邮箱验证实现，给邮箱发送对应验证码
    @Override
    public void sendByEmail(String email) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email); //收件人地址
        message.setFrom(from); //发件人地址
        message.setSubject(email);//发送的主题
        String code = getCode();
        //将验证码保存在缓存中
        //短信发送成功后将正确的短信验证码存储到缓存（redis）
        checkCodeFeign.saveToRedisByemail(email,code,30);
        //发送验证码
        message.setText("您的验证码是:"+code);//发送的内容
        emailSender.send(message);//发送信息

    }

    @Override
    public R send(String phone) throws Exception {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5t7rAAfxRqwgKEeXei4H")  //your-accessKeyId
                .accessKeySecret("thpcnIisgqwNcCsWbim9rvhJ7pmVSy")  //your-accessKeySecret
                //.securityToken("<your-token>") // use STS token
                .build());

        //创建异步请求客户端
        AsyncClient client = AsyncClient.builder()
                .region("cn-wuhan") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        //设置请求参数
        String code = getCode();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phone)
                .templateCode("SMS_276466212")
                .templateParam("{\"code\":\""+code+"\"}")
                .signName("千锋Java")
                .build();

        //请求发送短信
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        client.close();
        if("OK".equalsIgnoreCase(resp.getBody().getCode())){
            //短信发送成功后将正确的短信验证码存储到缓存（redis）
            checkCodeFeign.saveToRedis(phone,code,10);
            return R.ok(resp);
        }else{
            return R.fail();
        }
    }

    /**
     * 生成随机6位数字验证码
     * @return
     */
    private String getCode(){
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int)(Math.random()*10);
        }
        return code;
    }
}
