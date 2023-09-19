import { useRouter } from 'vue-router'
import http from '@/utils/http'
import { reactive } from 'vue'
import { Base64 } from 'js-base64'
import { message } from 'ant-design-vue'
import useTenant from '@/store/tenant.js'
import useProduct from '@/store/product.js'

export default function useLogin() {

    const router = useRouter()
    // 全局状态存储
    const tenantStore = useTenant()
    const productStore = useProduct()

    const modelRef = reactive({
        name: 'qfjava',
        pwd: '123456'
    })

    // 到达注册页面
    const toRegister = () => {
        router.push('/register')
    }

    const login = async () => {
        if(modelRef.name && modelRef.pwd) {
            const r = await http.post(`/auth/tenant/login`, modelRef)
            if(r.code === 200) {
                const token = r.message  // 获取到jwt
                const base64Payload = token.split('.')[1] // 获取payload
                /*
                * 1.encode 编码  decode 解码
                * 2.Base64.decode(base64Payload) 返回值是一个字符串
                * 3.JSON.parse() 这个方法是js本身就有的，将一个对象字符串(数组字符串)转换为js的对象或者数组
                */
                const payload = JSON.parse(Base64.decode(base64Payload))  // 返回的结果是字符串
                const tenantId = payload.sub // 租户号
                
                // 使用状态管理
                tenantStore.setInfo(modelRef.name, tenantId, token)
            
                router.push('/home/instance')
            }else {
                message.error(r.message)
            }
        }else {
            message.error('您还有参数没有输入')
        }
    }

    // 退出登录
    const logout = () => {
        tenantStore.$reset()
        productStore.$reset()
        router.push('/login')
    }

    return {
        modelRef,
        login,
        toRegister,
        logout
    }
}