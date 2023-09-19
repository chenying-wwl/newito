<script setup>
    import { ref, reactive } from 'vue'
    import http from '@/utils/http'
    import { message } from 'ant-design-vue'
    import { storeToRefs } from 'pinia'

    import useDeviceTypeStore from '@/store/deviceType.js'

    const deviceTypeStore = useDeviceTypeStore()

    const { deviceTypes } = storeToRefs(deviceTypeStore)

    const emits = defineEmits(['refresh'])

    const visible =  ref(false)

    const formRef = ref()

    defineProps({
        modelTypes: Array
    })

    // 展示模态框
    const showModal = async id => {
        const r = await http.get(`/user/model-template/id/${id}`)
        editForm.value = r.data
        visible.value = true
    }

    // 要提交的数据
    const editForm = ref({
        propertyName: '',
        deviceTypeId: '',
        modelType: '',
        description: ''
    })

    const rules = {
        propertyName: [
            { required: true, message: '物模型名称不能为空', trigger: 'change' }
        ],
        deviceTypeId: [
            { required: true, message: '设备类型不能为空', trigger: 'change' }
        ],
        modelType: [
            { required: true, message: '物模型类型不能为空', trigger: 'change' }
        ]
    }

    // 点击确认按钮提交
    const editModel = async () => {
        try {
            await formRef.value.validateFields() 
            try {
                const r = await http.post(`/user/model-template/update`, editForm.value)
                if(r.code === 200) {
                    message.success('操作成功')
                    
                    // 刷新数据
                    emits('refresh')
                    visible.value = false
                    formRef.value.resetFields()
                }else {
                    message.error('操作失败')
                }
            }catch {
                message.error('请求失败')
            }
        }catch {
            console.log('validate failed')
        }
    }

    // 隐藏模态框
    const hideModal = () => {
        visible.value = false
        formRef.value.resetFields()
    }

    defineExpose({
        showModal
    })
</script>
<template>
    <a-modal
        v-model:visible="visible"
        title="添加设备类型"
        :mask-closable="false"
        okText="确认"
        cancelText="取消"
        @cancel="hideModal"
        @ok="editModel">
        <a-form
            :model="editForm"
            name="basic"
            ref="formRef"
            :rules="rules"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
            autocomplete="off"
        >
            <a-form-item label="物模型名称" name="propertyName">
                <a-input v-model:value="editForm.propertyName" />
            </a-form-item>
            <a-form-item label="设备类型" name="deviceTypeId">
                <a-select
                    v-model:value="editForm.deviceTypeId"
                    style="width: 100%"
                    :options="deviceTypes.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item label="物模型类型" name="modelType">
                <a-select
                    v-model:value="editForm.modelType"
                    style="width: 100%"
                    :options="modelTypes.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item label="描述" name="description">
                <a-textarea v-model:value="editForm.description" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
:deep(.ant-form-item-explain-error) {
    font-size: 12px !important;
}
</style>