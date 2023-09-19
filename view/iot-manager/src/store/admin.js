import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('admins', {
    state: () => ({
        admins: []
    }),
    actions: {
        async getAllArea() {
            const r = await http.post(`/user/manager/all`)
            this.admins = [{code: '', name: '请选择管理员用户'}, ...r.data]
        }
    }
})