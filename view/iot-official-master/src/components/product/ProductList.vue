<script setup>
    import { ref, readonly } from 'vue'
    import { useRouter } from 'vue-router'
    import { RedoOutlined, SearchOutlined, DownOutlined } from '@ant-design/icons-vue'
    import useProduct from '@/hooks/useProduct'
    import ProductDetail from './ProductDetail.vue';

    const detailRef = ref()

    const router = useRouter()

    const { columns, data, flip, total, current, singleProduct } = useProduct()

    const showDetail = id => {
        singleProduct.value = data.value.find(item => item.id === id)
        detailRef.value.showModal()
    }

    // 变为只读
    const readOnySingleProduct = readonly(singleProduct)

    // 跳转到创建产品页面
    const createProd = () => {
        router.push('/home/create-product')
    }
</script>
<template>
    <div class="toolbar">
        <a-space>
            <a-button id="createProductBtn" type="primary" @click="createProd">创建产品</a-button>
            <!-- <a-button>快速入门</a-button> -->
            <!-- <a-input placeholder="请输入产品名称查询">
                <template #suffix>
                    <search-outlined />
                </template>
            </a-input> -->
            <!-- <a-input placeholder="请选择产品标签">
                <template #suffix>
                    <down-outlined />
                </template> -->
            <!-- </a-input> -->
        </a-space>
        <a-button>
            <redo-outlined />
        </a-button>
    </div>
    <a-table size="small" :columns="columns" :data-source="data" :pagination="false">
        <template #emptyText>
            <a-empty />
        </template>
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a-button size="small" type="link" @click="showDetail(record.id)" class="action-btn">{{ record.name }}</a-button>
            </template>
            <template v-else-if="column.key === 'action'">
                <a-button size="small" type="link" @click="showDetail(record.id)" class="action-btn">详情</a-button>
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
        :hide-on-single-page="true"
        style="margin-top: 1em; text-align: right;"/>
    <product-detail :product="readOnySingleProduct" ref="detailRef"></product-detail>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
.toolbar {
    margin: 1em 0;
    display: flex;
    justify-content: space-between;
}
.action-btn{
    font-size: @sm-size;
}
</style>