<script setup>
    import useDeviceListOfGroup from '@/hooks/useDeviceListOfGroup'

    const props = defineProps({
        id: String
    })

    const { columns, data, flip } = useDeviceListOfGroup(props.id)
</script>
<template>
    <!-- <a-space style="margin-bottom: 1em;">
        <a-button type="primary">添加设备到分组</a-button>
        <a-select style="width: 120px" placeholder="全部产品" />
    </a-space> -->
    <a-table size="small" :columns="columns" :data-source="data" :pagination="false">
        <template #emptyText>
            <a-empty />
        </template>
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a-button size="small" type="link" class="action-btn">{{ record.name }}</a-button>
            </template>
            <template v-else-if="column.key === 'action'">
                <a-button size="small" type="link" class="action-btn">详情</a-button>
                <a-button size="small" type="link" class="action-btn">删除</a-button>
            </template>
        </template>
    </a-table>
</template>
<style lang="less" scoped>
    
</style>