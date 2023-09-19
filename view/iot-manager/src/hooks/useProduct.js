import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
        username: '',
        productKey: '',
        areaCode: '',
        name: '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    const columns = reactive([                       
        { title: '产品名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '所属租户', dataIndex: 'tenantId', key: 'tenantId', align: 'center' },
        { title: '行政区', dataIndex: 'areaCode', key: 'areaCode', align: 'center' },
        { title: '用户名', dataIndex: 'username', key: 'username', align: 'center' },
        { title: '产品标识key', dataIndex: 'productKey', key: 'productKey', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/product/list`, searchForm)
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