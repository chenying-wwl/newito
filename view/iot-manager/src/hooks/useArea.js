import { reactive, ref, onMounted } from 'vue'
import http from '@/utils/http'

export default function() {
    const searchForm = reactive({
        code: '',
        name: '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const columns = reactive([                       
        { title: '行政区编码', dataIndex: 'code', key: 'code', align: 'center' },
        { title: '名称', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '等级', dataIndex: 'level', key: 'level', align: 'center' },
        { title: '类型', dataIndex: 'type', key: 'type', align: 'center' },
        { title: '简称',  dataIndex: 'abname', key: 'abname', align: 'center' },
        { title: '父编码',  dataIndex: 'pid', key: 'pid', align: 'center' },
        { title: '父名称', dataIndex: 'pname', key: 'pname', align: 'center' },
        { title: '备注', dataIndex: 'note', key: 'note', align: 'center' }
    ])

    const levelFilter = lv => {
        switch(lv) {
            case 1:
                return '省'
            case 2:
                return '地级市'
            case 3:
                return '区/县'
            case 4:
                return '乡/镇'
        }
    }
    const typeFilter = tp => {
        switch(tp) {
            case 1:
                return '省'
            case 2:
                return '自治区'
            case 3:
                return '直辖市'
            case 4:
                return '特别行政区'
            case 5:
                return '地级市'
            case 6:
                return '地区'
            case 7:
                return '地级市'
            case 8:
                return '盟'
            case 9:
                return '市辖区'
            case 10:
                return '县'
            case 11:
                return '县级市'
            case 12:
                return '自治县'
            case 13:
                return '旗'
            case 14:
                return '自治旗'
            case 15:
                return '特区'
            case 16:
                return '林区'
        }
    }

    const data = ref([])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/area/list`, searchForm)
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
        columns,
        data,
        total,
        flip,
        search,
        clear,
        formRef,
        levelFilter,
        typeFilter
    }
}