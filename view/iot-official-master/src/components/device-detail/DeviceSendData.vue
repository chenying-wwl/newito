<script setup>
    import { onMounted, ref, nextTick, onUnmounted } from 'vue'
    import http from '@/utils/http.js'
    import useTenant from '@/store/tenant'
    import { storeToRefs } from 'pinia'

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    const props = defineProps({
        device: Object
    })

    const reports = ref([])

    const getDeviceData = async () => {
        const r1 = await http.get(`/tenant/model-cmd/list?tenantId=${tenantId.value}&productKey=${props.device.productDto.productKey}&deviceKey=${props.device.deviceKey}`)
        reports.value = r1.data.records
    }

    let timer = null

    onMounted(async () => {
        getDeviceData()
        timer = setInterval(() => {
            getDeviceData()
        }, 10000)
    })

    onUnmounted(() => {
        console.log('组件卸载...');
        clearInterval(timer)
    })
</script>
<template>
    <el-row>
        <el-col :span="24" >
            <div id="logBox" v-if="reports.length > 0" class="log-box">
                <p v-for="rp in reports" :key="rp.id" class="log-item">
                    {{ JSON.stringify(rp) }}
                </p>
            </div>
            <span v-else class="no-data">
                暂无数据
            </span>
        </el-col>
    </el-row>
</template>
<style lang="less" scoped>
    .log-box {
        width: 100%;
        max-height: 30em;
        border-radius: 0.3em;
        padding: 1em;
        background-color: #dce9f6;
        overflow-y: scroll;
        box-sizing: border-box;
        .log-item {
            box-sizing: border-box;
            margin-bottom: 1em;
            border-radius: 0.4em;
            word-wrap: break-word;
            padding: 0.4em;
            
            &:hover {
                background-color: #fff;
            }
        }
    }
    .no-data {
        padding: 1em;
        font-weight: 600;
    }
</style>