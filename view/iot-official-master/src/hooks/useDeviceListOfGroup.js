import { ref, reactive, onMounted } from "vue"
import http from '@/utils/http.js'

export default function(id) {
    const data = ref([])
    const total = ref(0)
    const size = ref(5)
    const current = ref(1)

    const columns = reactive([
        { title: 'DeviceName/备注名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '设备所属产品', dataIndex: 'productName', key: 'productName', align: 'center' },
        { title: '节点类型', dataIndex: 'deviceType', key: 'deviceType', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    const flip = async (page = 1) => {
        const r = await http.get(`/tenant/device/list?groupId=${id}&page.current=${page}&page.size=${size.value}`)
        console.log(r)
        total.value = r.data.total
        data.value = r.data.records
        current.value = r.data.current
    }

    onMounted(() => {
        flip(current.value)
    })

    return {
        data,
        columns,
        total,
        flip
    }
}