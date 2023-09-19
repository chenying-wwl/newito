<script setup>
    import { ref, onMounted } from 'vue'
    import { useRouter } from 'vue-router'
    // import { countdown } from 'countdown'
    import VueCountdown from '@chenfengyuan/vue-countdown';

    const router = useRouter()

    const onCountdownEnd = () => {
      router.push('/login')
    }

    //  const sec = ref(0)
    // onMounted(() => {
    //     let v = 5
    //     const timer = countdown(new Date(), s => {
    //       sec.value = v - s.seconds
    //       if(sec.value === 0) {
    //         window.clearInterval(timer)
    //         router.push('/login')
    //       }
    //     }, countdown.SECONDS)
    // })
</script>
<template>
  <a-layout>
    <login-and-register-header>
    </login-and-register-header>
    <a-layout>
      <a-layout-content>
        <a-row>
          <a-col :span="10" :offset="6">
            <a-result
                status="success"
                title="注册成功"
            >
                <template #subTitle>
                    <vue-countdown @end="onCountdownEnd" :time="5" v-slot="{ seconds }">
                      <p>还有 {{ seconds }}s 跳转到登录页面</p>
                    </vue-countdown>
                </template>
                <template #extra>
                    <a-button @click="() => router.push('/login')" type="link">去登录</a-button>
                </template>
            </a-result>
          </a-col>
        </a-row>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<style lang="less" scoped>
@import '@/assets/global.less';
</style>