import { reactive, ref } from 'vue'
import http from '@/utils/http.js'
import useTenant from '@/store/tenant'
import { storeToRefs } from 'pinia'
import { message } from 'ant-design-vue'

export default function() {

    const visible = ref(false)
    const formRef = ref()
    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    const modelRef = reactive({
        groupName: '',
        description: '',
        tenantId
    })

    // 展示模态框
    const showModal = () => {
        visible.value = true
    }

    const addGroup = async emits => {
        await http.post(`/tenant/group/add`, modelRef)
        visible.value = false
        // 清空表单
        formRef.value.resetFields()
        message.success('设备分组成功')
        emits('refresh')
    }

    return {
        visible,
        modelRef,
        showModal,
        addGroup,
        formRef
    }
}