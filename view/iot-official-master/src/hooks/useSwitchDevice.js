import http from '@/utils/http.js'
import { Modal } from 'ant-design-vue'
import { createVNode} from 'vue'
import { ExclamationCircleOutlined } from '@ant-design/icons-vue'

export default function() {
    const switchDevice = async (deviceKey, name) => {
        Modal.confirm({
            title: 'Confirm',
            icon: createVNode(ExclamationCircleOutlined),
            content: `确定要${ turn ? '关闭' : '开启' }"${name}" 设备吗?`,
            okText: '确认',
            cancelText: '取消',
            async onOk() {
                // await http.post(`/api/cmd/send`, {
                //     deviceKey,
                //     cmdName: 'on_off', 
                //     value: turn ? '0' : '1',
                //     deviceSubCode: 'xxx'
                // })
                // const r = data.value.find(item => item.deviceKey === deviceKey)
                // console.log(r)
                r.turn = !turn
                // console.log(r)
            }
        })
    }

    return {
        switchDevice
    }
}