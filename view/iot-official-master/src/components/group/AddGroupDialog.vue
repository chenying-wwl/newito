<script setup>
    import useAddGroup from '@/hooks/useAddGroup.js'

    const { modelRef, visible, showModal, addGroup, formRef } =  useAddGroup()
    
    const emits = defineEmits(['refresh'])

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
        @ok="addGroup(emits)">
        <a-form layout="vertical" ref="formRef" :model="modelRef">
            <a-form-item name="groupName">
                <template #label>
                    <span style="color: red;">*</span> 分组名称
                </template>
                <a-input v-model:value="modelRef.groupName" placeholder="请输入分组名称" />
            </a-form-item>
            <a-form-item name="description">
                <template #label>
                    分组描述
                </template>
                <a-textarea v-model:value="modelRef.description" :auto-size="true" placeholder="请输入分组描述" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
    
</style>