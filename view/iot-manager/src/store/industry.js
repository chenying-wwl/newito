import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('industries', {
    state: () => ({
        industries: []
    }),
    actions: {
        async getAllIndustry() {
            const r = await http.post(`/user/industry/all`)
            this.industries = [{id: '', name: '请选择设备行业'}, ...r.data]
        }
    }
})
