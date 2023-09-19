<script setup>
    import { ref, reactive } from 'vue'
    import http from '@/utils/http'
    import { message } from 'ant-design-vue'
    import useIndustry from '@/store/industry.js'
    import { storeToRefs } from 'pinia'
    
    const industryStore = useIndustry()

    const { industries } = storeToRefs(industryStore)

    const emits = defineEmits(['refresh'])

    const visible =  ref(false)

    const formRef = ref()

    defineProps({
        deviceTypes: Array
    })

    // 展示模态框
    const showModal = async () => {
        visible.value = true
    }

    // 要提交的数据
    const addForm = reactive({
        name: '',
        deviceType: '',
        industryId: ''
    })

    const rules = {
        name: [
            { required: true, message: '设备类型名称不能为空', trigger: 'change' }
        ],
        deviceType: [
            { required: true, message: '设备类型不能为空', trigger: 'change' }
        ],
        industryId: [
            { required: true, message: '设备所属行业不能为空', trigger: 'change' }
        ]
    }

    // 点击确认按钮提交
    const addDeviceType = async () => {
        try {
            await formRef.value.validateFields() 
            try {
                const r = await http.post(`/user/device-type/add`, addForm)
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
        @ok="addDeviceType">
        <a-form
            :model="addForm"
            name="basic"
            ref="formRef"
            :rules="rules"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
            autocomplete="off"
        >
            <a-form-item label="设备类型名称" name="name">
                <a-input v-model:value="addForm.name" />
            </a-form-item>
            <a-form-item label="设备所属行业" name="industryId">
                <a-select
                    v-model:value="addForm.industryId"
                    style="width: 100%"
                    :options="industries.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
            <a-form-item label="设备类型" name="deviceType">
                <a-select
                    v-model:value="addForm.deviceType"
                    style="width: 100%"
                    :options="deviceTypes.map(c => ({value: c.id, label: c.name}))"
                ></a-select>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
:deep(.ant-form-item-explain-error) {
    font-size: 12px !important;
}
</style>