<script setup>
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'

    const { gotoDevice } = useBreadcrumb()
</script>
<template>
     <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item>孪生引擎</a-breadcrumb-item>
        </a-breadcrumb>
        <h3>功能正在研发中, 敬请期待...</h3>
    </div>
</template>
<style lang="less" scoped>
    
</style>