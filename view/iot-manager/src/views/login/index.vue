<script setup>
    import { reactive } from 'vue'
    import http from '@/utils/http'
    import Cookies from 'js-cookie'
    import { message } from 'ant-design-vue'
    import { useRouter } from 'vue-router'

    const loginForm = reactive({
        userName: 'qf',
        password: 'qf'
    })

    const router = useRouter()

    // 成功
    const onFinish = async () => {
        try {
            const r = await http.post(`/user/auth/doLogin`, loginForm)
            Cookies.set('token', r.message)
            router.push('/home/area')
        }catch {
            message.error('用户名或密码错误')
        }
    }

    // 失败
    const onFinishFailed = errorInfo => {
       console.log('failed.');
    }
</script>
<template>
    <div style="padding-top: 100px;">
        <a-row>
            <a-col :span="8" :offset="8">
                <a-card hoverable style="width: 100%">
                    <template #title>
                        <h3>登录</h3>
                    </template>
                    
                    <a-form
                        :model="loginForm"
                        name="basic"
                        :label-col="{ span: 4 }"
                        :wrapper-col="{ span: 20 }"
                        autocomplete="off"
                        @finish="onFinish"
                        @finishFailed="onFinishFailed"
                    >
                        <a-form-item
                            label="Username"
                            name="userName"
                            :rules="[{ required: true, message: '请输入用户名' }]"
                        >
                            <a-input v-model:value="loginForm.userName" />
                        </a-form-item>

                        <a-form-item
                            label="Password"
                            name="password"
                            :rules="[{ required: true, message: '请输入密码' }]"
                        >
                            <a-input-password v-model:value="loginForm.password" />
                        </a-form-item>

                        <a-form-item :wrapper-col="{ offset: 4, span: 20 }">
                            <a-button type="primary" block html-type="submit">提交</a-button>
                        </a-form-item>
                    </a-form>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>
<style lang="less" scoped>
    :deep(.ant-form-item-explain-error) {
        font-size: 12px !important;
    }
</style>