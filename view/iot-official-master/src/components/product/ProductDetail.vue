<script setup>
    import { defineProps, ref } from 'vue'
    
    const visible = ref(false)

    // 展示模态框
    const showModal = () => {
        visible.value = true
    }

    const handleOk = e => {
      visible.value = false;
    };

    defineProps({
        product: Object
    })

    defineExpose({
        showModal
    })

</script>
<template>
    <a-modal
        v-model:visible="visible"
        title="产品详情"
        width="850px"
        :mask-closable="false"
        :footer="null" @ok="handleOk">
        <a-descriptions bordered title="产品详情" :column="2" size="small">
            <a-descriptions-item label="产品ID">{{ product.id }}</a-descriptions-item>
            <a-descriptions-item label="产品名称">{{ product.name }}</a-descriptions-item>
            <a-descriptions-item label="ProductKey">{{ product.productKey }}</a-descriptions-item>
            <a-descriptions-item label="用户名">{{  product.username }}</a-descriptions-item>
            <a-descriptions-item label="密码">{{  product.password }}</a-descriptions-item>
            <a-descriptions-item label="创建时间">{{  product.createTime }}</a-descriptions-item>
        </a-descriptions>
    </a-modal>
    
</template>
<style lang="less" scoped>
    
</style>