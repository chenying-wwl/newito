<script setup>
    import { ref } from 'vue'
    import { SearchOutlined, ClearOutlined, PlusCircleOutlined } from '@ant-design/icons-vue'
    import useDeviceType from '@/hooks/useDeviceType'
    import AddDeviceTypeDialog from '@/components/device-type/AddDeviceTypeDialog.vue'
    import EditDeviceTypeDialog from '@/components/device-type/EditDeviceTypeDialog.vue'
    import useIndustry from '@/store/industry.js'
    import { storeToRefs } from 'pinia'
    
    const industryStore = useIndustry()

    const { industries } = storeToRefs(industryStore)

    const {data, columns, searchForm, total, flip, search,
        clear, formRef, deviceTypes } = useDeviceType()

    const addRef = ref()
    const editRef = ref()

    const showAddModal = id => {
        addRef.value.showModal()
    }

    const showEditModal = id => {
        editRef.value.showModal(id)
    }

    // 过滤器
    const filterIndustry = id => {
        return industries.value.find(item => item.id === id).name
    }

</script>
<template>
    <div style="display: flex; justify-content: space-between;">
        <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
            <a-form-item name="name">
                <a-input v-model:value="searchForm.name" placeholder="请输入设备名称"></a-input>
            </a-form-item>
            <a-form-item name="industryId">
                <a-select
                    v-model:value="searchForm.industryId"
                    style="width: 170px"
                    :options="industries.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item name="deviceType">
                <a-select
                    v-model:value="searchForm.deviceType"
                    style="width: 170px"
                    :options="deviceTypes.map(c => ({value: c.id, label: c.name}))"
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
            <template v-if="column.key === 'industryId'">
                {{ filterIndustry(record.industryId) }}
            </template>
            <template v-if="column.key === 'deviceType'">
                <template v-if="record.deviceType === 1">
                    <a-tag color="pink">网关</a-tag>
                </template>
                <template v-if="record.deviceType === 2">
                    <a-tag color="orange">传感器</a-tag>
                </template>
                <template v-if="record.deviceType === 3">
                    <a-tag color="cyan">继电器</a-tag>
                </template>
                <template v-if="record.deviceType === 4">
                    <a-tag color="purple">摄像头</a-tag>
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
    <add-device-type-dialog
        :deviceTypes="deviceTypes"
        ref="addRef"
        @refresh="flip"></add-device-type-dialog>
    <edit-device-type-dialog
        :deviceTypes="deviceTypes"
        ref="editRef"
        @refresh="flip"></edit-device-type-dialog>
</template>
<style lang="less" scoped>
.action-btn{
    font-size: 12px;
}   
</style>