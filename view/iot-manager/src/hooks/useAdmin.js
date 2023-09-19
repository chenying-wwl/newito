import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
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
        { title: '用户名称', dataIndex: 'userName', key: 'userName', align: 'center' },
        { title: '创建日期', password: 'password', password: 'password', align: 'center' },
        { title: '创建日期', dataIndex: 'email', key: 'email', align: 'center' },
        { title: '创建日期', dataIndex: 'createTime', key: 'createTime', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/manager/list`, searchForm)
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