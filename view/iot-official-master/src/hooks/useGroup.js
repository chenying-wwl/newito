import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http.js'
import useTenant from '@/store/tenant'
import { storeToRefs } from 'pinia'

export default function () {
    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    const data = ref([])
    const total = ref(0)
    const size = ref(5)
    const current = ref(1)

    onMounted(() => {
        flip(current.value)
    })

    // 表头信息
    const columns = reactive([
        { title: '分组名称', dataIndex: 'groupName', key: 'groupName', align: 'center' },
        { title: '分组ID', dataIndex: 'id', key: 'id', align: 'center' },
        { title: '创建时间', key: 'createTime', align: 'center', dataIndex: 'createTime' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    // 翻页
    const flip = async (page = 1) => {
        const r = await http.get(`/tenant/group/list?tenantId=${tenantId.value}&page.size=${size.value}&page.current=${page}`)
        total.value = r.data.total
        data.value = r.data.records
        current.value = r.data.current
    }

    return {
        columns,
        data,
        flip,
        total,
        current
    }
}