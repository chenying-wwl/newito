<script setup>
    import { ref, reactive } from 'vue'
    import http from '@/utils/http'
    import { message } from 'ant-design-vue'

    const emits = defineEmits(['refresh'])

    const visible =  ref(false)

    const formRef = ref()

    // 展示模态框
    const showModal = async () => {
        visible.value = true
    }

    // 要提交的数据
    const addForm = reactive({
        name: ''
    })

    const rules = {
        name: [
            { required: true, message: '行业名称不能未空', trigger: 'change' }
        ]
    }

    // 点击确认按钮提交
    const addIndustry = async () => {
        try {
            await formRef.value.validateFields() 
            try {
                const r = await http.post(`/user/industry/add`, addForm)
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
        title="添加行业"
        :mask-closable="false"
        okText="确认"
        cancelText="取消"
        @cancel="hideModal"
        @ok="addIndustry">
        <a-form
            :model="addForm"
            name="basic"
            ref="formRef"
            :rules="rules"
            :label-col="{ span: 4 }"
            :wrapper-col="{ span: 20 }"
            autocomplete="off"
        >
            <a-form-item label="行业名称" name="name">
                <a-input v-model:value="addForm.name" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
:deep(.ant-form-item-explain-error) {
    font-size: 12px !important;
}
</style>