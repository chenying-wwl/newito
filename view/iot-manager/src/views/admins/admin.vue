<script setup>
    import { ref } from 'vue'
    import { SearchOutlined, ClearOutlined, PlusCircleOutlined } from '@ant-design/icons-vue'
    import useIndustry from '@/hooks/useIndustry'
    import AddIndustryDialog from '@/components/industry/AddIndustryDialog.vue'
    import EditIndustryDialog from '@/components/industry/EditIndustryDialog.vue'

    const {data, columns, searchForm, total, flip, search, clear, formRef } = useIndustry()

    const addRef = ref()
    const editRef = ref()

    const showAddModal = id => {
        addRef.value.showModal(id)
    }

    const showEditModal = id => {
        editRef.value.showModal(id)
    }
</script>
<template>
    <div style="display: flex; justify-content: space-between;">
        <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
            <a-form-item name="name">
                <a-input v-model:value="searchForm.name" placeholder="请输入行业名称"></a-input>
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
    <add-industry-dialog ref="addRef" @refresh="flip"></add-industry-dialog>
    <edit-industry-dialog ref="editRef" @refresh="flip"></edit-industry-dialog>
</template>
<style lang="less" scoped>
.action-btn{
    font-size: 12px;
}
</style>