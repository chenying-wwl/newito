import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
        active: '',
        deviceKey: '',
        deviceTypeId: '',
        name: '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    const actives = reactive([
        {id: '', name: '请选择设备激活状态'},
        {id: '2', name: '激活'},
        {id: '1', name: '未激活'},
    ])

    const columns = reactive([                       
        { title: '设备名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '设备标识', dataIndex: 'deviceKey', key: 'deviceKey', align: 'center' },
        { title: '设备类型', dataIndex: 'deviceTypeId', key: 'deviceTypeId', align: 'center' },
        { title: '所属产品', dataIndex: 'productId', key: 'productId', align: 'center' },
        { title: '父设备', dataIndex: 'parentId', key: 'parentId', align: 'center' },
        { title: '所属租户', dataIndex: 'tenantId', key: 'tenantId', align: 'center' },
        { title: '激活状态', dataIndex: 'active', key: 'active', align: 'center' },
        { title: '在线状态', dataIndex: 'state', key: 'state', align: 'center' },
        { title: '创建日期', dataIndex: 'createTime', key: 'createTime', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/device/list`, searchForm)
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
        formRef,
        actives
    }
}