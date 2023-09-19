<script setup>
  import { useRouter } from 'vue-router'
  import LoginAndRegisterHeader from '@/components/LoginAndRegisterHeader.vue'
  import QrCode from '@/components/QRCode.vue'
  import RegisterPanel from '@/components/register/RegisterPanel.vue'

  const router = useRouter()

  // 到达登录页面
  const goLogin = () => {
    router.push('/login')
  }

</script>
<template>
  <a-layout>
    <login-and-register-header>
      <template #title>
          注册
      </template>
    </login-and-register-header>
    <a-layout>
      <a-layout-content>
        <a-row>
          <a-col :span="10" :offset="10">
            <a-card style="margin: 2em 0;">
              <div class="switch-icon" @click="goLogin">登录</div>
              <qr-code style="position: relative; top: 5em;"></qr-code>
              <register-panel></register-panel>
              <a-card-grid class="extra-info" :hoverable="false">
                注册视为您已同意第三方账号绑定协议、服务条款、隐私政策
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