<script setup>
    import useLogin from '@/hooks/useLogin.js'

    const { modelRef, login } = useLogin()
</script>
<template>
    <a-form autocomplete="off">
        <a-form-item name="username">
            <a-input :bordered="false" v-model:value.trim="modelRef.name" class="inp" placeholder="输入邮箱/账号"/>
        </a-form-item>
        <a-form-item name="password">
            <a-input-password :bordered="false" v-model:value.trim="modelRef.pwd" class="inp" placeholder="请输入密码"/>
        </a-form-item>
        <a-form-item style="margin-top: 3em;">
            <a-button type="primary" size="large" @click="login" block html-type="submit">登录</a-button>
            <a-button type="link" class="forget-password">忘记密码</a-button>
        </a-form-item>
    </a-form>
</template>

<style lang="less" scoped>
    .inp {
        border-bottom: 1px solid #d7d8d9;
        caret-color: #ff6a00;
        &:hover {
            border-bottom: 1px solid #ff6a00;
        }
    }
    .forget-password {
        font-size: 12px;
        color: #999;
        cursor: pointer;
        float: right;
    }
</style>