<script setup>
    import { SearchOutlined, ClearOutlined } from '@ant-design/icons-vue'
    import useTenant from '@/hooks/useTenant'

    const {data, columns, searchForm, total, flip, search, 
        clear, formRef, enableOrDisable } = useTenant('manager')

</script>
<template>
    <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
        <a-form-item name="name">
            <a-input v-model:value="searchForm.name" placeholder="请输入租户名"></a-input>
        </a-form-item>
        <a-form-item name="accessKey">
            <a-input v-model:value="searchForm.accessKey" placeholder="请输入租户key"></a-input>
        </a-form-item>
        <a-form-item>
            <a-space>
                <a-button @click="search" type="primary">
                    <template #icon><search-outlined /></template>
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
            <template v-if="column.key === 'enable'">
                <template v-if="record.enable === 1">
                    <a-tag color="#87d068">可用</a-tag>
                </template>
                <template v-else>
                    <a-tag color="#f50">不可用</a-tag>
                </template>
            </template>
            <template v-if="column.key === 'auditStatus'">
                <template v-if="record.auditStatus === 0">
                    <a-tag color="default">待审核</a-tag>
                </template>
                <template v-else-if="record.auditStatus === 1">
                    <a-tag color="#87d068">通过</a-tag>
                </template>
                <template v-else-if="record.auditStatus === 2">
                    <a-tag color="#f50">不通过</a-tag>
                </template>
            </template>
            <template v-else-if="column.key === 'action'">
                <template v-if="record.enable === 1">
                    <a-button size="small" type="link" @click="enableOrDisable(record.id, 0)" class="action-btn disable-btn">禁用</a-button>
                </template>
                <template v-else>
                    <a-button size="small" type="link" @click="enableOrDisable(record.id, 1)" class="action-btn">启用</a-button>
                </template>  
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
.action-btn{
    font-size: 12px;
}
.disable-btn {
    color: #f50;
}
</style>