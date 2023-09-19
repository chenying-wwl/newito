import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
        deviceTypeId: '',
        modelType: '',
        propertyName: '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    // 设备类型
    const modelTypes = reactive([
        {id: '', name: '请选择物模型类型'},
        {id: 1, name: '属性'},
        {id: 2, name: '事件'},
        {id: 3, name: '服务'}
    ])

    const columns = reactive([                       
        { title: '物模型名称', dataIndex: 'propertyName', key: 'propertyName', align: 'center' },
        { title: '设备类型', dataIndex: 'deviceTypeId', key: 'deviceTypeId', align: 'center' },
        { title: '物模型类型', dataIndex: 'modelType', key: 'modelType', align: 'center' },
        { title: '描述', dataIndex: 'description', key: 'description', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/model-template/list`, searchForm)
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
        modelTypes
    }
}