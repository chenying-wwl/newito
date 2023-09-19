<script setup>
    import { ref } from 'vue'
    import DeviceListOfGroup from '@/components/group-detail/DeviceListOfGroup.vue';
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'
    const { gotoDevice, gotoGroup } = useBreadcrumb()

    const props = defineProps({
        id: String
    })

    const activeKey = ref('2')
</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoGroup">分组</a></a-breadcrumb-item>
            <a-breadcrumb-item>分组1004</a-breadcrumb-item>
        </a-breadcrumb>
        <!-- {{ id }} -->
        <!-- <device-summary></device-summary> -->
        <a-tabs v-model:activeKey="activeKey" type="card" size="small" :tab-bar-gutter="0">
            <!-- <a-tab-pane key="1" tab="分组信息"> -->
                <!-- <device-detail-info></device-detail-info> -->
            <!-- </a-tab-pane> -->
            <a-tab-pane key="2" tab="设备列表">
                <device-list-of-group :id="props.id"></device-list-of-group>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<style lang="less" scoped>
    
</style>