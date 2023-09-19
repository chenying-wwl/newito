import axios from 'axios'
import { message } from 'ant-design-vue'
// import { useRouter } from 'vue-router'
// import { storeToRefs } from 'pinia'
// import store from '@/store'
import Cookies from 'js-cookie'

const http = axios.create({
    baseURL: 'http://localhost:10086'
})

/** */
http.interceptors.request.use(config => {
    const token = Cookies.get('token')
    if(token) {
      config.headers.token = token
    }
    return config
})


// 响应拦截配置
http.interceptors.response.use(res => {
    return res.data
}, err => {
    if(err.response) {npm
        if(code === 401) {
            message.info('会话失效, 您将重新登录')
        }
    } 
    Promise.reject(err)
})

export default http
