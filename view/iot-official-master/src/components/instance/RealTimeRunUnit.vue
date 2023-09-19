<script setup>

</script>
<template>
    <a-card style="height: 100% !important;">
        <template #title>
            <slot name="title"></slot>
        </template>
        <template #extra>
            <a-range-picker show-time format="YYYY/MM/DD HH:mm:ss" />
        </template>
        <slot name="chart"></slot>
    </a-card>
</template>
<style lang="less" scoped>
    :deep(.ant-card-body) {
        padding: 0;
    }
</style>