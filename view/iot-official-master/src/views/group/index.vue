<script setup>
    import { ref } from 'vue'
    import useGroup from '@/hooks/useGroup.js'
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'
    import { SearchOutlined, DownOutlined } from '@ant-design/icons-vue'
    import AddGroupDialog from '@/components/group/AddGroupDialog.vue'
    import { useRouter } from 'vue-router'

    const { gotoDevice } = useBreadcrumb()
    const { columns, data, current, total, flip } = useGroup()
    const router = useRouter()

    const addGp = ref()

    const addGroup = () => {
        addGp.value.showModal()
    }

    const gotoDetail = id => {
        router.push(`/home/group-detail/${id}`)
    }

</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item>分组</a-breadcrumb-item>
        </a-breadcrumb>
        <h1 class="title">分组</h1>
        <a-space style="margin: 1em 0;">
            <a-button @click="addGroup" type="primary">创建分组</a-button>
            <a-input placeholder="请输入产品名称查询">
                <template #suffix>
                    <search-outlined />
                </template>
            </a-input>
            <a-input placeholder="请选择产品标签">
                <template #suffix>
                    <down-outlined />
                </template>
            </a-input>
        </a-space>
        <a-table size="small" :columns="columns" :data-source="data" :pagination="false">
            <template #emptyText>
                <a-empty />
            </template>
            <template #bodyCell="{ column, record }">
                <template v-if="column.key === 'groupName'">
                    <a-button size="small" type="link" @click="gotoDetail(record.id)" class="action-btn">{{ record.groupName }}</a-button>
                </template>
                <template v-else-if="column.key === 'action'">
                    <a-button size="small" type="link" @click="gotoDetail(record.id)" class="action-btn">详情</a-button>
                    <!-- <a-button size="small" type="link" class="action-btn">删除</a-button> -->
                </template>
            </template>
        </a-table>
        <a-pagination 
            v-model:current="current" 
            :page-size="5"
            size="small"
            :total="total"
            show-less-items
            @change="flip"
            style="margin-top: 1em; text-align: right;"/>
        <add-group-dialog @refresh="flip(current)" ref="addGp"></add-group-dialog>
    </div>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
.title {
    font-weight: 600;
    font-size: 2em;
}
.action-btn{
    font-size: @sm-size;
}
</style>