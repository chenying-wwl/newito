<script setup>
    import DeviceList from '@/components/device/DeviceList.vue';

</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item>设备</a-breadcrumb-item>
        </a-breadcrumb>
        <h1 class="title">设备</h1>
        <device-list></device-list> 
    </div>
</template>
<style lang="less" scoped>
    .title {
        font-weight: 600;
        font-size: 2em;
    }
</style>