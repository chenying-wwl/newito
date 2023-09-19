<script setup>
    import LoginAndRegisterHeader from '@/components/LoginAndRegisterHeader.vue'
    import QrCode from '@/components/QRCode.vue'
    import LoginPanel from '@/components/login/LoginPanel.vue'
    import useLogin from '@/hooks/useLogin.js'

    const { toRegister } = useLogin()

</script>
<template>
  <a-layout>
    <login-and-register-header>
      <template #title>
        登录
      </template>
    </login-and-register-header>
    <a-layout>
      <a-layout-content>
        <a-row>
          <a-col :span="10" :offset="10">
            <a-card style="margin: 5em 0; position: relative;">
              <div class="switch-icon" @click="toRegister">注册</div>
              <qr-code></qr-code>
              <login-panel></login-panel>
              <a-card-grid class="extra-info" :hoverable="false">
                登录视为您已同意第三方账号绑定协议、服务条款、隐私政策
              </a-card-grid>
            </a-card>
          </a-col>
        </a-row>
      </a-layout-content>
    </a-layout>
  </a-layout>   
</template>

<style lang="less" scoped>
@import '@/assets/global.less';
@import '@/assets/sign.less';
</style>