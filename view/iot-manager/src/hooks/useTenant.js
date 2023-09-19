import { reactive, ref, onMounted, createVNode } from 'vue'
import { ExclamationCircleOutlined } from '@ant-design/icons-vue'
import http from '@/utils/http'
import { message, Modal } from 'ant-design-vue'

export default function(pg = 'examine') {
    const searchForm = reactive({
        accessKey: '',
        name: '',
        auditStatus: pg === 'examine' ?  0 : '',
        page: {
            current: 1,
            size: 10
        }
    })

    const total = ref(0)

    const formRef = ref()

    const data = ref([])

    const columns = reactive([                       
        { title: '租户名', dataIndex: 'name', key: 'name', align: 'center' },
        { title: '租户key', dataIndex: 'accessKey', key: 'accessKey', align: 'center' },
        { title: '公司名称', dataIndex: 'companyName', key: 'companyName', align: 'center' },
        { title: '执照编号', dataIndex: 'companyCode', key: 'companyCode', align: 'center' },
        { title: '手机号',  dataIndex: 'phone', key: 'phone', align: 'center' },
        { title: '是否可用',  dataIndex: 'enable', key: 'enable', align: 'center' },
        { title: '描述', dataIndex: 'description', key: 'description', align: 'center' },
        { title: '状态', dataIndex: 'auditStatus', key: 'auditStatus', align: 'center' },
        { title: '操作', key: 'action', align: 'center' }
    ])

    onMounted(async () => {
        flip()
    })

    const flip = async () => {
        const r = await http.post(`/user/tenant/list`, searchForm)
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

    // 审核通过
    const yesOrNO = (id, status = 1) => {
        Modal.confirm({
            title: `你确定要${status === 1 ? '通过' : '不通过' }该租户吗?`,
            icon: createVNode(ExclamationCircleOutlined),
            okText: '确认',
            okType: 'danger',
            cancelText: '取消',
            async onOk() {
                try {
                    const r = await http.get(`/user/tenant/${status === 1 ? 'checkyes' : 'checkno'}/id/${id}`)
                    if(r.code === 200) {
                        message.success('操作成功')
                        flip()
                    }else {
                        message.error('操作失败')
                    }
                }catch {
                    message.error('请求失败')
                }   
            }
        })
    }

    // 启用或者禁用
    const enableOrDisable = (id, enable = 1) => {
        Modal.confirm({
            title: `你确定要${enable === 1 ? '启用' : '禁用' }该租户吗?`,
            icon: createVNode(ExclamationCircleOutlined),
            okText: '确认',
            okType: 'danger',
            cancelText: '取消',
            async onOk() {
                try {
                    const r = await http.get(`/user/tenant/${enable === 1 ? 'open' : 'close'}/id/${id}`)
                    if(r.code === 200) {
                        message.success('操作成功')
                        flip()
                    }else {
                        message.error('操作失败')
                    }
                }catch {
                    message.error('请求失败')
                }   
            }
        })
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
        yesOrNO,
        enableOrDisable
    }
}