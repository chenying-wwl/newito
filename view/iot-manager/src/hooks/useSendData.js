import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function(deviceKey) {
    const searchForm = reactive({
        deviceKey: deviceKey,
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    const columns = reactive([                    
        { title: '租户', dataIndex: 'tenantId', key: 'tenantId', align: 'center' },
        { title: '产品', dataIndex: 'productKey', key: 'productKey', align: 'center'},
        { title: '设备', dataIndex: 'deviceKey', key: 'deviceKey', align: 'center' },
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', align: 'center' },
        { title: '传感器/继电器编号', dataIndex: 'deviceSubCode', key: 'deviceSubCode', align: 'center' },
        { title: '指令名称', dataIndex: 'cmdName', key: 'cmdName', align: 'center' },
        { title: '指令值', dataIndex: 'value', key: 'value', align: 'center' }
    ])

    onMounted(async () => {
        http.get(`/user/device/deviceKey/${deviceKey}`)
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/cmd/list`, searchForm)
        data.value = r.data.records
        total.value = r.data.total
    }

    const search = () => {
        searchForm.page.current = 1
        flip()
    }

    const clear = () => {
        formRef.value.resetFields()
        flip()
    }


    return {
        searchForm,
        flip,
        data,
        total,
        search,
        clear,
        columns,
        formRef
    }
}