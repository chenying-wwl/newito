<script setup>
    import { ref, readonly, toRef } from 'vue'
    import DeviceRecap from '@/components/device-detail/DeviceRecap.vue'
    import DeviceDetailInfo from '@/components/device-detail/DeviceDetailInfo.vue'
    import DeviceTopicList from '@/components/device-detail/DeviceTopicList.vue'
    import DeviceModelInfo from '@/components/device-detail/DeviceModelInfo.vue'
    import DeviceReportedData from '@/components/device-detail/DeviceReportedData.vue'
    import DeviceSendData from '@/components/device-detail/DeviceSendData.vue'
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'
    import useDeviceDetail from '@/hooks/useDeviceDetail.js'

    const { gotoDevice } = useBreadcrumb()

    const props = defineProps({
        id: String
    })

    const { detail } = useDeviceDetail(props.id)

    const readonlyDetail = readonly(detail)

    const activeKey = ref('1')
</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item>设备详情</a-breadcrumb-item>
        </a-breadcrumb>
        <device-recap :readonlyDetail="readonlyDetail"></device-recap>
        <a-tabs v-model:activeKey="activeKey" type="card" size="small" :tab-bar-gutter="0" :destroy-inactive-tab-pane="true">
            <a-tab-pane key="1" tab="设备信息">
                <device-detail-info :readonlyDetail="readonlyDetail"></device-detail-info>
            </a-tab-pane>
            <a-tab-pane key="2" tab="Topic列表">
                <device-topic-list :deviceKey="detail.deviceKey"></device-topic-list>
            </a-tab-pane>
            <a-tab-pane key="3" tab="物模型数据">
                <device-model-info :deviceTypeId="detail.deviceTypeId" :deviceKey="detail.deviceKey"></device-model-info>
            </a-tab-pane>
            <a-tab-pane key="4" tab="上报数据">
               <device-reported-data :device="readonlyDetail"></device-reported-data>
            </a-tab-pane>
            <a-tab-pane key="5" tab="发送数据">
               <device-send-data :device="readonlyDetail"></device-send-data>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<style lang="less" scoped>

</style>