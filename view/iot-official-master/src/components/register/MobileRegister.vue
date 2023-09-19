<script setup>

</script>
<template>
    <a-form autocomplete="off">
        <a-form-item name="mobileNumber">
            <a-input placeholder="请输入手机号" :bordered="false" class="inp">
                <template #prefix>
                    <span>+86</span>
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="code">
            <a-input placeholder="请输入验证码" :bordered="false" class="inp">
                <template #suffix>
                    <a-button type="link" style="color: #999;">获取验证码</a-button>
                </template>
            </a-input>
        </a-form-item>
        <a-form-item>
            <a-button type="primary" size="large" block html-type="submit">注册</a-button>
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