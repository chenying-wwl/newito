import { reactive, ref, onMounted } from 'vue'
import useProduct from '@/store/product'
import { storeToRefs } from 'pinia'

// 因为页面一进来就获取到了所有的产品，并进行状态存储
// 后续所有关于产品的操作都是状态管理中获取
export default function () {
    const data = ref([])
    const size = ref(5)
    const current = ref(1)
    const singleProduct = ref({})
    const productStore = useProduct()

    const { total } = storeToRefs(productStore)

    onMounted(() => {
        flip(current.value)
    })

    // 表头信息
    const columns = reactive([
        { title: '产品名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: 'ProductKey', dataIndex: 'productKey', key: 'productKey', align: 'center' },
        { title: '创建时间', key: 'createTime', align: 'center', dataIndex: 'createTime' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    // 翻页
    const flip = async (page = 1) => {
        const r = productStore.flip(page, size.value)
        data.value = r
    }

    return {
        columns,
        data,
        flip,
        total,
        current,
        singleProduct
    }
}