<script setup>
    import { ref, reactive } from 'vue'
    import http from '@/utils/http.js'
    import { message } from 'ant-design-vue'

    const formRef = ref()
    const visible = ref(false)

    const cmdModel = reactive({
        cmdName: '',
        deviceKey: '',
        deviceSubCode: 0,
        value: 0
    })

    const showModal = (name, key) => {
        cmdModel.cmdName = name
        cmdModel.deviceKey = key
        visible.value = true
    }

    defineExpose({
        showModal
    })

    // 发送指令
    const sendCmd = async () => {
        const r = await http.post(`/user/cmd/send`, cmdModel)
        if(r.code === 200) {
            message.success('指令发送成功')
            visible.value = false
            formRef.value.resetFields()
        }else {
            message.error('指令发送失败')
        }
    }
</script>
<template>
    <a-modal
        v-model:visible="visible"
        title="发送指令"
        :mask-closable="false"
        okText="确认"
        cancelText="取消"
        @ok="sendCmd(emits)">
        <a-form layout="vertical" ref="formRef" :model="cmdModel">
            <a-form-item label="设备名称" name="cmdName">
                <a-input v-model:value="cmdModel.cmdName" :disabled="true" placeholder="请输入设备名称" />
            </a-form-item>
            <a-form-item label="设备key" name="deviceKey">
                <a-input v-model:value="cmdModel.deviceKey" :disabled="true" placeholder="请输入设备名称" />
            </a-form-item>
            <a-form-item label="继电器编号" name="deviceSubCode">
                <a-input-number v-model:value="cmdModel.deviceSubCode" style="width: 100%" :min="0" />
            </a-form-item>
            <!-- <a-form-item label="指令名称">
                <template #label>
                    <span style="color: red;">*</span> 设备类型
                </template>
                <a-select
                    v-model:value="cmdModel.deviceTypeId"
                    :options="deviceTypes.map(d => ({value: d.id, label: d.name}))"
                ></a-select>
            </a-form-item> -->
            <a-form-item label="开关指令" name="value">
                <a-select
                    v-model:value="cmdModel.value"
                    :options="[{label: '开', value: 1}, {label: '关', value: 0}]"
                ></a-select>
            </a-form-item> 
        </a-form>
    </a-modal>
</template>
<style lang="less" scoped>
    
</style>