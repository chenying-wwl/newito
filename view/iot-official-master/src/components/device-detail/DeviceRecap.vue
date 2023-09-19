<script setup>
    import { ArrowLeftOutlined } from '@ant-design/icons-vue'
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'
    import { RedoOutlined } from '@ant-design/icons-vue'
    const { gotoDevice } = useBreadcrumb()
    import http from '@/utils/http.js'
    import { ref, onMounted } from 'vue'

    const online = ref(false)

    const switchDevice = (name, key) => {
        switchDeviceDg.value.showModal(name, key)
    }

    const switchDeviceDg = ref()

    const props = defineProps({
        readonlyDetail: Object
    })

    // 获取设备的在线状态
    const getOnlineOfDevice = async () => {
        const r = await http.get(`/tenant/device/isOnline/${props.readonlyDetail.productDto.productKey}/${props.readonlyDetail.deviceKey}`)
        online.value = r.data
    }

    onMounted(() => {
        setTimeout(() => {
            getOnlineOfDevice()
        }, 600)  
    })

</script>
<template>
    <a-descriptions size="small" :column="2" style="margin: 1em 0;">
        <template #title>
            <arrow-left-outlined @click="gotoDevice"/>
            {{ readonlyDetail.name }}
            <template v-if="readonlyDetail.deviceType === '3'">
                <a-button type="link" danger @click="switchDevice(readonlyDetail.name, readonlyDetail.deviceKey)">发送指令</a-button>
                <!-- <a-switch
                    size="small"
                    :checked="false"
                    @change="switchDevice(readonlyDetail.name, readonlyDetail.deviceKey)" 
                    checked-children="开" un-checked-children="关" />  -->
            </template>
        </template>
        <a-descriptions-item>
            <template #label>
                <span class="title">产品</span>
            </template>
            {{ readonlyDetail.productDto.name }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">设备类型</span>
            </template>
            <template v-if="readonlyDetail.deviceType === '1'">
                网关设备
            </template>
            <template v-else-if="readonlyDetail.deviceType === '2'">
                传感器
            </template>
            <template v-else-if="readonlyDetail.deviceType === '3'">
                继电器                                                                                
            </template>
            <template v-else>
                摄像头
            </template>
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">deviceKey</span>
            </template>
            {{ readonlyDetail.deviceKey }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">deviceSecret</span>
            </template>
            {{ readonlyDetail.deviceSecret }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">在线状态</span>
            </template>
            <span v-if="online">
                <i class="dot green-dot"></i> 在线
            </span>
            <span v-else>
                <i class="dot red-dot"></i> 离线
            </span>
            <redo-outlined class="refresh-online-state" @click="getOnlineOfDevice"/>
        </a-descriptions-item>
    </a-descriptions>
    <switch-device-dialog ref="switchDeviceDg"></switch-device-dialog>
</template>
<style lang="less" scoped>
:deep(.ant-descriptions-header) {
    margin-bottom: 0.6em;
}
.title {
    font-weight: 600;
}
.dot {
    height: 0.5em; 
    width: 0.5em;
    display: inline-block;
    border-radius: 50%;
    position: relative;
    bottom: 1px;
}
.red-dot {
    border: 1px solid red;
    background-color: red;
}
.green-dot {
    border: 1px solid green;
    background-color: green;
}

.refresh-online-state {
    margin-left: 1em; 
    cursor: pointer;
}
.refresh-online-state:hover {
    color: red;
}
</style>