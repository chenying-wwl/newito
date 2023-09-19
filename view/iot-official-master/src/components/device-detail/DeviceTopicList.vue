<script setup>
    import { reactive, ref, onMounted } from 'vue'
    import http from '@/utils/http.js'

    const columns = reactive([
        { title: '设备的Topic', dataIndex: 'topic', key: 'topic', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    const props = defineProps({
        deviceKey: String
    })

    const data = ref([])

    onMounted(async () => {
        const r = await http.get(`/tenant/device-topic/list/${props.deviceKey}`)
        data.value = r.data
    })
</script>
<template>
    <p class="title">已订阅 Topic 列表</p>
    <a-table size="small" :columns="columns" :data-source="data" :pagination="false">
        <template #emptyText>
            <a-empty />
        </template>
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a-button size="small" type="link" class="action-btn">{{ record.name }}</a-button>
            </template>
            <template v-else-if="column.key === 'action'">
                <a-button size="small" disabled type="link" class="action-btn">详情</a-button>
                <a-button size="small" disabled type="link" class="action-btn">删除</a-button>
            </template>
        </template>
    </a-table>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
.action-btn{
    font-size: @sm-size;
} 
.title {
    font-weight: 600;
    font-size: 1em;
    padding-bottom: 1em;
} 
</style>