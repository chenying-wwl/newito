import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
        deviceType: '',
        industryId: '',
        name: '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    // 设备类型
    const deviceTypes = reactive([
        {id: '', name: '请选择设备类型'},
        {id: 1, name: '网关'},
        {id: 2, name: '传感器'},
        {id: 3, name: '继电器'},
        {id: 4, name: '摄像头'}
    ])

    const columns = reactive([                       
        { title: '设备类型名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '所属行业', dataIndex: 'industryId', key: 'industryId', align: 'center' },
        { title: '设备类型', dataIndex: 'deviceType', key: 'deviceType', align: 'center' },
        { title: '创建日期', dataIndex: 'createTime', key: 'createTime', align: 'center' },
        { title: '修改日期', dataIndex: 'updateTime', key: 'updateTime', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/device-type/list`, searchForm)
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
        deviceTypes
    }
}