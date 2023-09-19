<script setup>
    import { SearchOutlined, ClearOutlined } from '@ant-design/icons-vue'
    import useProduct from '@/hooks/useProduct'
    import { storeToRefs } from 'pinia'

    import useArea from '@/store/area.js'
    import useTenant from '@/store/tenant.js'

    const areaStore = useArea()
    const tenantStore = useTenant()

    const { areas } = storeToRefs(areaStore)
    const { tenants } = storeToRefs(tenantStore)

    const {data, columns, searchForm, total, flip, search, 
        clear, formRef } = useProduct()

    const filterTenant = id => {
        return tenants.value.find(item => item.id === id).name
    }

    const filterArea = code => {
        return areas.value.find(item => item.code === code).name
    }

</script>
<template>
    <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
        <a-form-item name="name">
            <a-input v-model:value="searchForm.name" placeholder="请输入产品名称"></a-input>
        </a-form-item>
        <a-form-item name="username">
            <a-input v-model:value="searchForm.username" placeholder="请输入用户名"></a-input>
        </a-form-item>
        <a-form-item name="productKey">
            <a-input v-model:value="searchForm.productKey" placeholder="请输入产品标识"></a-input>
        </a-form-item>
        <a-form-item name="areaCode">
            <a-select
                v-model:value="searchForm.areaCode"
                style="width: 170px"
                :options="areas.map(c => ({value: c.code, label: c.name}))"
            ></a-select>
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
            <template v-if="column.key === 'tenantId'">
                <a-tag color="green">{{ filterTenant(record.tenantId) }}</a-tag>
            </template>
            <template v-if="column.key === 'areaCode'">
                {{ filterArea(record.areaCode) }}
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