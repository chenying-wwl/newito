<script setup>
    import AccessTutorial from "@/components/product/AccessTutorial.vue"
    import ProductList from "@/components/product/ProductList.vue"
    import useBreadcrumb from '@/hooks/useBreadcrumb.js'

    const { gotoDevice } = useBreadcrumb()
</script>
<template>
    <div style="background-color: #fff; padding: 0 24px 24px; flex: 1;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item><a href="javascript: void(0)" @click="gotoDevice">设备管理</a></a-breadcrumb-item>
            <a-breadcrumb-item>产品</a-breadcrumb-item>
        </a-breadcrumb>
        <access-tutorial></access-tutorial>
        <product-list></product-list>
    </div>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
.tutorial {
    border: 1px solid transparent;
    box-shadow: 0 1px 4px 0 rgba(0,0,0,.08);
    background-color: #fff;
}
</style>