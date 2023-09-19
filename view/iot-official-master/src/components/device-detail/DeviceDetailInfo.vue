<script setup>
    defineProps({
        readonlyDetail: Object
    })
</script>
<template>
    <a-descriptions title="设备信息" size="small">
        <a-descriptions-item>
            <template #label>
                <span class="title">产品名称</span>
            </template>
            {{ readonlyDetail.productDto.name }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">ProductKey</span>
            </template>
            {{ readonlyDetail.productDto.productKey }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">地域</span>
            </template>
            {{ readonlyDetail.productDto.areaName }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">DeviceName</span>
            </template>
            {{ readonlyDetail.name }}
        </a-descriptions-item>
        <a-descriptions-item>
            <template #label>
                <span class="title">创建时间</span>
            </template>
            {{ readonlyDetail.createTime }}
        </a-descriptions-item>
        <!-- <a-descriptions-item>
            <template #label>
                <span class="title">所属分组</span>
            </template>
        </a-descriptions-item> -->
    </a-descriptions>
</template>
<style lang="less" scoped>
.title {
    font-weight: 600;
}
</style>