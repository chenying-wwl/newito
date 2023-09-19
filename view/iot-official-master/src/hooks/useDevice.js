import { reactive, ref, onMounted, createVNode, watch } from 'vue'
import http from '@/utils/http.js'
import { message, Modal } from 'ant-design-vue'
import router from '../router'
import useTenant from '@/store/tenant'
import { storeToRefs } from 'pinia'
import { ExclamationCircleOutlined } from '@ant-design/icons-vue'

export default function useProduct() {
    
    const data = ref([])
    const total = ref(0)
    const size = ref(5)
    const current = ref(1)
    const productId = ref(0)

    // 设备总数、激活设备总数、运行设备总数
    const deviceNumbers = reactive({
        total: 0,
        active: 0,
        online: 0
    })

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    onMounted(async () => {
        // flip(current.value)
        flip()
        getDeviceNumberInfo()
    })

    // 查询所有、在线、激活设备的数量，当productId 为0的时候查询所有 
    const getDeviceNumberInfo = async (productId = 0) => {
        const r = await http.get(`/tenant/device/count/${tenantId.value}/${productId}`)
        deviceNumbers.total = r.data.allCount
        deviceNumbers.active = r.data.activeCount
        deviceNumbers.online = r.data.onLineCount
    }

    // 表头信息
    const columns = reactive([
        { title: '设备名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '所属产品', dataIndex: 'productName', key: 'productName', align: 'center' },
        { title: 'Key', dataIndex: 'deviceKey', key: 'deviceKey', align: 'center' },
        { title: '设备激活状态', dataIndex: 'active', key: 'active', align: 'center' },
        { title: '开/关设备', key: 'deviceSwitch', align: 'center' },
        { title: '创建时间', key: 'createTime', align: 'center', dataIndex: 'createTime' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    // 翻页
    const flip = async (page = 1) => {
        /**
        const r = await http.get(`/tenant/device/list?tenantId=${tenantId.value}` + 
                `&page.size=${size.value}&page.current=${page}&`) 
        */
        const url = productId.value === 0 ? `/tenant/device/listByTenant/${tenantId.value}` 
            : `/tenant/device/listByProduct/${productId.value}`
        const r = await http.get(url) 
        const tmpData = r.data

        // 目的是想再前端排序，在线设备排在最上面，其次是激活的设备，再其次是未激活的设备
        const onlineDevices = []
        const activeDevice = []
        const unActiveDeivce = []

        for(let i = 0; i < tmpData.length; i++) {
            let d = tmpData[i]
            const r = await http.get(`/tenant/device/isOnline/${d.productKey}/${d.deviceKey}`)
            if(r.data) {
                d.online = true
                onlineDevices.push(d)
            }else {
                d.online = false
                // 激活的设备
                if(d.active === 2) {
                    activeDevice.push(d)
                }else {  // 未激活的设备
                    unActiveDeivce.push(d)
                }
            }
        }

        data.value = [...onlineDevices, ...activeDevice, ...unActiveDeivce]
    }

    const showDetail = id => {
        router.push(`/home/device-detail/${id}`)
    }

    // 监听产品id的变化，重新获取数量
    watch(productId, n => {
        getDeviceNumberInfo(n)
        flip()
    })

    const refreshDeviceInfo = () => {
        getDeviceNumberInfo(productId.value)
        flip()
    }

    // 删除设备
    const deleteDevice = async id => {
        Modal.confirm({
            title: '你确定要删除设备吗?',
            icon: createVNode(ExclamationCircleOutlined),
            content: '数据的删除是不可逆的',
            okText: '确认',
            okType: 'danger',
            cancelText: '取消',
            async onOk() {
                const r = await http.delete(`/tenant/device/del/${id}`)
                console.log(r)
                if(r.code === 200) {
                    message.success('设备删除成功')
                    flip(current.value)
                }
            }
          })
    }

    return {
        columns,
        data,
        flip,
        total,
        current,
        showDetail,
        deleteDevice,
        productId,
        deviceNumbers,
        refreshDeviceInfo
    }
}