<script setup>
    import { ref } from 'vue'
    import { SearchOutlined, ClearOutlined, PlusCircleOutlined } from '@ant-design/icons-vue'
    import useDeviceType from '@/hooks/useDeviceType'
    import useModel from '@/hooks/useModel'
    import AddModelDialog from '@/components/model/AddModelDialog.vue'
    import EditModelDialog from '@/components/model/EditModelDialog.vue'
    import { storeToRefs } from 'pinia'

    import useDeviceTypeStore from '@/store/deviceType.js'

    const deviceTypeStore = useDeviceTypeStore()

    const { deviceTypes } = storeToRefs(deviceTypeStore)

    const {data, columns, searchForm, total, flip, search,
        clear, formRef, modelTypes } = useModel()

    const addRef = ref()
    const editRef = ref()

    const showAddModal = id => {
        addRef.value.showModal()
    }

    const showEditModal = id => {
        editRef.value.showModal(id)
    }

    // 过滤器
    const filterDeviceType = id => {
        return deviceTypes.value.find(item => item.id === id).name
    }

</script>
<template>
    <div style="display: flex; justify-content: space-between;">
        <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
            <a-form-item name="propertyName">
                <a-input v-model:value="searchForm.propertyName" placeholder="请输入模型名称"></a-input>
            </a-form-item>
            <a-form-item name="deviceTypeId">
                <a-select
                    v-model:value="searchForm.deviceTypeId"
                    style="width: 170px"
                    :options="deviceTypes.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item name="modelType">
                <a-select
                    v-model:value="searchForm.modelType"
                    style="width: 170px"
                    :options="modelTypes.map(c => ({value: c.id, label: c.name}))"
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
        <a-button @click="showAddModal" type="primary">
            <plus-circle-outlined />新增
        </a-button>
    </div>
    <a-table size="small" style="margin-top: 1em;" :columns="columns" :data-source="data" :pagination="false">
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'deviceTypeId'">
                {{ filterDeviceType(record.deviceTypeId) }}
            </template>
            <template v-if="column.key === 'modelType'">
                <template v-if="record.modelType === 1">
                    <a-tag color="pink">属性</a-tag>
                </template>
                <template v-if="record.modelType === 2">
                    <a-tag color="orange">事件</a-tag>
                </template>
                <template v-if="record.modelType === 3">
                    <a-tag color="cyan">服务</a-tag>
                </template>
            </template>
            <template v-if="column.key === 'action'">
                <a-button size="small" type="link" @click="showEditModal(record.id)" class="action-btn">编辑</a-button>
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
    <add-model-dialog :modelTypes="modelTypes" ref="addRef" @refresh="flip"></add-model-dialog>
    <edit-model-dialog :modelTypes="modelTypes" ref="editRef" @refresh="flip"></edit-model-dialog>
</template>
<style lang="less" scoped>
.action-btn{
    font-size: 12px;
}   
</style>