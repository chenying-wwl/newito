<script setup>
    import { SearchOutlined, ClearOutlined } from '@ant-design/icons-vue'
    import useArea from '@/hooks/useArea'

    const {data, columns, searchForm, total, flip, search, 
        clear, formRef, typeFilter, levelFilter } = useArea()

</script>
<template>
    <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
        <a-form-item name="code">
            <a-input v-model:value="searchForm.code" placeholder="请输入行政区编码"></a-input>
        </a-form-item>
        <a-form-item name="name">
            <a-input v-model:value="searchForm.name" placeholder="请输入行政区名称"></a-input>
        </a-form-item>
        <a-form-item>
            <a-space>
                <a-button @click="search" type="primary">
                    <template #icon><SearchOutlined /></template>
                    搜索
                </a-button>
                <a-button @click="clear(formRef)" type="danger">
                    <template #icon><clear-outlined /></template>
                    清空
                </a-button>
            </a-space>
        </a-form-item>
    </a-form>
    <a-table size="small" style="margin-top: 1em;" :columns="columns" :data-source="data" :pagination="false">
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'level'">
                {{ levelFilter(record.level) }}
            </template>
            <template v-if="column.key === 'type'">
                {{ typeFilter(record.type) }}
            </template>
        </template>
    </a-table>
    <a-pagination 
        v-model:current="searchForm.page.current"
        :total="total"
        :show-size-changer="false"
        @change="flip"
        :hide-on-single-page="true"
        show-less-items
        size="small"
        style="margin-top: 1em; text-align: right;"/>
</template>
<style lang="less" scoped>
    
</style>