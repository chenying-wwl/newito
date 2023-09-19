import { reactive, ref, onMounted, watch } from 'vue'
import http from '@/utils/http.js'
import { message } from 'ant-design-vue'
import useTenant from '@/store/tenant'
import { storeToRefs } from 'pinia'

export default function() {
    const formRef = ref()
    const visible = ref(false)
    
    const deviceTypes = ref([])
    const topDevices = ref([])

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    const modelRef = reactive({
        name: '',
        deviceTypeId: '',
        deviceNo: 0,
        productId: '',
        prarentId: 0,
        tenantId,
        groupId: 0
    })

    //  获取所有的设备类型
    const getAllDeviceType = async () => {
        const r = await http.get(`/tenant/device-type/all`)
        deviceTypes.value = r.data
    }

    // TODO 监听产品ID, 切换一级设备
    watch(() => modelRef.productId, async n => {
        console.log(n)
        const r = await http.get(`/tenant/device/list?tenantId=${tenantId.value}&parentId=0&productId=${n}`)
        topDevices.value = r.data.records
    })

    // // 获取所有的一级设备，应为业务规则中设备只有两级
    // const getAllTopDevices = async () => {
    //     const r = await http.get(`/tenant/device/parentId/0`)
    //     topDevices.value = r.data
    // }

    onMounted(() => {
        getAllDeviceType()
    })

    // 展示模态框
    const showModal = () => {
        visible.value = true
    }

    const addDevice = async (emits) => {
        await http.post(`/tenant/device/add`, modelRef)
        visible.value = false   
        formRef.value.resetFields()     
        message.success('设备添加成功')
        emits('refresh')
    } 

    return {
        visible,
        modelRef,
        deviceTypes,
        topDevices,
        showModal,
        addDevice,
        formRef
    }
}