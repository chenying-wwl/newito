import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import router from '@/router'
import pn from '@/store/index.js'

const app = createApp(App)

app.use(Antd)
    .use(router)
    .use(pn)
    
app.mount('#app')