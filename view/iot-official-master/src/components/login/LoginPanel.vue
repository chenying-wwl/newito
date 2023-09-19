<script setup>
    import { ref } from 'vue'
    import AccountLogin from './AccountLogin.vue'
    // import MobileLogin from './MobileLogin.vue'

    const activeKey = ref('1')
</script>
<template lang="">
    <a-card-grid :hoverable="false" style="width: 55%; text-align: center;">
        <a-tabs v-model:activeKey="activeKey">
            <a-tab-pane key="1" tab="账号登录">
                <account-login></account-login>
            </a-tab-pane>
            <!-- <a-tab-pane key="2" tab="手机号登录" force-render>
                <mobile-login></mobile-login>
            </a-tab-pane> -->
        </a-tabs>
    </a-card-grid>
</template>

<style lang="less" scoped>
</style>