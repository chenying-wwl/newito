<script setup>
    // import { ref } from 'vue'
    // const activeTab = ref('1')
    import { ArrowLeftOutlined } from '@ant-design/icons-vue'
    import CreateProductForm from '@/components/create-product/CreateProductForm.vue'
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'

    const { gotoDevice, gotoProduct } = useBreadcrumb()
    
</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoProduct">产品</a></a-breadcrumb-item>
            <a-breadcrumb-item>创建产品</a-breadcrumb-item>
        </a-breadcrumb>
        <div class="title">
            <span>
                <arrow-left-outlined @click="gotoProduct" /> 新建产品
            </span>
            <span>
                （设备模型）
            </span>
        </div>
        <create-product-form></create-product-form>
        <!-- <a-tabs v-model:activeKey="activeTab" type="card">
            <a-tab-pane key="1" tab="新建产品">
                <create-product-form></create-product-form>
            </a-tab-pane>
            <a-tab-pane key="2" tab="从设备中心新建产品">

            </a-tab-pane>
        </a-tabs> -->
    </div>
</template>
<style lang="less" scoped>
.title {
    margin-bottom: 1em;
    > span:first-of-type {
        font-weight: 600;
        font-size: 25px;
    }
    > span:last-of-type {
        font-size: 14px;
    }
}
</style>