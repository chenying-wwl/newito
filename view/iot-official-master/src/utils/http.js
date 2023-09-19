import axios from 'axios'
import { message } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'
import store from '@/store'
import useTenant from '@/store/tenant'
import useProduct from '@/store/product.js'
import JSONBIG from 'json-bigint'

const http = axios.create({
    baseURL: 'http://10.41.57.126:10086',
    // transformResponse: [
    //     data => {
    //         const json = JSONBIG({
    //             storeAsString: true
    //         })
    //         return json.parse(data)
    //     }
    // ]
})

http.interceptors.request.use(config => {
    const tenantStore = useTenant(store)
    const { token } = storeToRefs(tenantStore)
    if(token.value) {
      config.headers.token = token.value
    }
    return config
})

// 响应拦截配置
http.interceptors.response.use(res => {
    return res.data
}, err => {
    if(err.response) {
        const code = err.response.status
        if(code === 500) {
            message.info('服务器内部错误, 请将问题反馈系统 owner, 我们将尽快解决')
        }
        if(code === 401) {
            message.info('会话失效, 您将重新登录')
            const tenantStore = useTenant(store)
            const productStore = useProduct(store)
            const { router } = useRouter()
            // 清空状态数据
            tenantStore.$reset()
            productStore.$reset()
            // 跳到登录页面
            router.push('/login')
        }
    }
    Promise.reject(err)
})

export default http
