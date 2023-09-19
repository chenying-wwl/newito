<script setup>
    import useAddDevice from '@/hooks/useAddDevice.js'

    const { modelRef, deviceTypes, topDevices, formRef,
        visible, showModal, addDevice } =  useAddDevice()

    const emits = defineEmits(['refresh'])

    defineProps({
        products: Array
    })

    defineExpose({
        showModal
    })
</script>
<template>
    <a-modal
        v-model:visible="visible"
        title="添加设备"
        :mask-closable="false"
        okText="确认"
        cancelText="取消"
        @ok="addDevice(emits)">
        <a-form layout="vertical" ref="formRef" :model="modelRef">
            <a-form-item name="productId">
                <template #label>
                    <span style="color: red;">*</span> 产品
                </template>
                <a-select
                    v-model:value="modelRef.productId"
                    :options="products.map(p => ({value: p.id, label: p.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item name="deviceTypeId">
                <template #label>
                    <span style="color: red;">*</span> 设备类型
                </template>
                <a-select
                    v-model:value="modelRef.deviceTypeId"
                    :options="deviceTypes.map(d => ({value: d.id, label: d.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item name="deviceNo">
                <template #label>
                    <span style="color: red;">*</span> 设备编号(同一个产品下设备编号不能重复复)
                </template>
                <a-input-number style="width: 100%" v-model:value="modelRef.deviceNo" :min="0" />
            </a-form-item>
            <a-form-item label="父设备(非必选项)" name="prarentId">
                <a-select
                    allowClear
                    v-model:value="modelRef.prarentId"
                    :options="topDevices.map(t => ({value: t.id, label: t.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item name="name">
                <template #label>
                    <span style="color: red;">*</span> 设备名称
                </template>
                <a-input v-model:value="modelRef.name" placeholder="请输入设备名称" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
    
</style>