<script setup>
    import { ref } from 'vue'
    import AccountRegister from './AccountRegister.vue'
    import MobileRegister from './MobileRegister.vue'

    const activeKey = ref('1')
</script>
<template lang="">
    <a-card-grid :hoverable="false" style="width: 55%; text-align: center;">
        <a-tabs v-model:activeKey="activeKey">
            <a-tab-pane key="1" tab="账号注册">
                <account-register></account-register>
            </a-tab-pane>
            <!-- <a-tab-pane key="2" tab="手机号注册(暂不开放)" force-render>
                <mobile-register></mobile-register>
            </a-tab-pane> -->
        </a-tabs>
    </a-card-grid>
</template>

<style lang="less" scoped>
</style>