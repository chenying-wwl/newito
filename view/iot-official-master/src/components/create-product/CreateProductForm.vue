<script setup>
    import useCreateProduct from '@/hooks/useCreateProduct.js';
    const { modelRef, addProduct, provinces, cities, areas, showArea } = useCreateProduct()
</script>
<template>
    <a-form layout="vertical" :wrapper-col="{ span: 8 }">
        <a-form-item>
            <template #label>
                <span style="color: red;">*</span><span>产品名称</span>
            </template>
            <a-input v-model:value="modelRef.name" placeholder="请输入产品名称" />
        </a-form-item>
        <a-form-item>
            <template #label>
                <span style="color: red;">*</span> 所属区域
            </template>
            <a-space>
                <a-select
                    v-model:value="modelRef.province"
                    style="width: 120px"
                    :options="provinces.map(p => ({value: p.code, label: p.name}))"
                ></a-select>
                <a-select
                    v-model:value="modelRef.city"
                    style="width: 120px"
                    :options="cities.map(c => ({value: c.code, label: c.name}))"
                ></a-select>
                <a-select
                    v-if="showArea"
                    v-model:value="modelRef.area"
                    style="width: 120px"
                    :options="areas.map(a => ({value: a.code, label: a.name}))"
                ></a-select>
            </a-space>
        </a-form-item>
        <a-form-item>
            <a-button type="primary" @click="addProduct">提交</a-button>
        </a-form-item>
    </a-form>
</template>
<style lang="less" scoped>
    
</style>