<script setup>
    import { reactive, ref  } from 'vue'
    import http from '@/utils/http'
    import { message } from 'ant-design-vue'
    import useIndustry from '@/store/industry.js'
    import { storeToRefs } from 'pinia'
    
    const industryStore = useIndustry()

    const { industries } = storeToRefs(industryStore)

    const emits = defineEmits(['refresh'])

    const visible =  ref(false)

    const formRef = ref()

    const cmds = ref([])

    // 展示模态框
    const showModal = async deviceKey => {
        const r = await http.get(`/user/device/deviceKey/${deviceKey}`)
        cmdForm.name = r.data.name
        cmdForm.deviceKey = r.data.deviceKey

        // 指令下拉列表
        const r1 = await http.post(`/user/model-template/select`)
        const tmp = []

        Object.keys(r1.data).forEach(k => {
            tmp.push({cmdName: k, showName: r1.data[k]})
        })
        cmds.value = tmp

        visible.value = true
    }

    // 要提交的数据
    const cmdForm = reactive({
        name: '',
        cmdName: '',
        deviceKey: '',
        value: '',
        deviceSubCode: ''
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
                const r = await http.post(`/user/cmd/send`, cmdForm)
                if(r.code === 200) {
                    message.success('操作成功')
                    
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
        title="向设备发送指令"
        :mask-closable="false"
        okText="确认"
        cancelText="取消"
        @cancel="hideModal"
        @ok="addDeviceType">
        <a-form
            :model="cmdForm"
            name="basic"
            ref="formRef"
            :rules="rules"
            :label-col="{ span: 4 }"
            :wrapper-col="{ span: 20 }"
            autocomplete="off"
        >
            <a-form-item label="设备名称" name="name">
                <a-input disabled v-model:value="cmdForm.name"/>
            </a-form-item>
            <a-form-item label="设备key" name="deviceKey">
                <a-input disabled v-model:value="cmdForm.deviceKey" />
            </a-form-item>
            <a-form-item label="继电器编号" name="deviceSubCode">
                <a-input v-model:value="cmdForm.deviceSubCode" placeholder="请输入继电器编号"/>
            </a-form-item>
            <a-form-item label="指令名称" name="cmdName">
                <a-select
                    v-model:value="cmdForm.cmdName"
                    style="width: 100%"
                    placeholder="选择指令名称"
                    :options="cmds.map(c => ({value: c.cmdName, label: c.showName}))"
                ></a-select>
            </a-form-item>
            <a-form-item label="指令值" name="value">
                <a-input v-model:value="cmdForm.value" placeholder="请输入指令值" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
:deep(.ant-form-item-explain-error) {
    font-size: 12px !important;
}
</style>