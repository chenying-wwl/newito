<script setup>
    import { ref, reactive } from 'vue'
    import http from '@/utils/http'
    import { message } from 'ant-design-vue'

    const emits = defineEmits(['refresh'])

    const visible =  ref(false)

    const formRef = ref()

    // 展示模态框
    const showModal = async id => {
        const r = await http.get(`/user/industry/id/${id}`)
        editForm.value = r.data
        visible.value = true
    }

    // 要提交的数
    const editForm = ref({})

    // 点击确认按钮提交
    const editIndustry = async () => {
        try {
            await formRef.value.validateFields() 
            try {
                const r = await http.post(`/user/industry/update`, editForm.value)
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

    const rules = {
        name: [
            { required: true, message: '行业名称不能未空', trigger: 'change' }
        ]
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
        @ok="editIndustry">
        <a-form
            :model="editForm"
            name="basic"
            ref="formRef"
            :rules="rules"
            :label-col="{ span: 4 }"
            :wrapper-col="{ span: 20 }"
            autocomplete="off"
        >
            <a-form-item label="行业名称" name="name">
                <a-input v-model:value="editForm.name" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
:deep(.ant-form-item-explain-error) {
    font-size: 12px !important;
}
</style>