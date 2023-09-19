<script setup>
    import useRegister from '@/hooks/useRegister.js'
    const { modelRef, onSubmit, sendCode, validateName } = useRegister()

</script>
<template>
    <a-form autocomplete="off">
        <a-form-item name="name">
            <a-input :bordered="false" @blur="validateName" v-model:value.trim="modelRef.name" class="inp" placeholder="输入登录名"/>
        </a-form-item>
        <a-form-item name="pwd">
            <a-input-password :bordered="false" v-model:value.trim="modelRef.pwd" class="inp" placeholder="输入登录密码"/>
        </a-form-item>
        <a-form-item name="repeatPwd">
            <a-input-password :bordered="false" v-model:value.trim="modelRef.repeatPwd" class="inp" placeholder="请再次输入登录密码"/>
        </a-form-item>
        <a-form-item name="companyName">
            <a-input :bordered="false" v-model:value.trim="modelRef.companyName" class="inp" placeholder="请输入公司名称"/>
        </a-form-item>
        <a-form-item name="companyCode">
            <a-input :bordered="false" v-model:value.trim="modelRef.companyCode" class="inp" placeholder="请输入公司编码"/>
        </a-form-item>
        <a-form-item name="phone">
            <a-input placeholder="请输入手机号" v-model:value.trim="modelRef.phone" :bordered="false" class="inp">
                <template #prefix>
                    <span>+86</span>
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="code">
            <a-input placeholder="请输入验证码" v-model:value.trim="modelRef.code"  :bordered="false" class="inp">
                <template #suffix>
                    <a-button @click="sendCode" type="link" style="color: #999;">发送</a-button>
                </template>
            </a-input>
        </a-form-item>
        <a-form-item>
            <a-button type="primary" size="large" @click.prevent="onSubmit" block html-type="submit">注册</a-button>
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
    .ant-form-item {
        margin-bottom: 1.2em;
    }
    .forget-password {
        font-size: 12px;
        color: #999;
        cursor: pointer;
        float: right;
    }
</style>