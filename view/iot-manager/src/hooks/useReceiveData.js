import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function(deviceKey) {
    const searchForm = reactive({
        device: deviceKey,
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    const columns = reactive([                       
        { title: '租户', dataIndex: 'tenant_id', key: 'tenant_id', align: 'center' },
        { title: '产品', dataIndex: 'product_key', key: 'product_key', align: 'center'},
        { title: '设备', dataIndex: 'device', key: 'device', align: 'center' },
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', align: 'center' },
        { title: '数据值', dataIndex: 'data', key: 'data', width: 650 }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/deviceModelValue/list`, searchForm)
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