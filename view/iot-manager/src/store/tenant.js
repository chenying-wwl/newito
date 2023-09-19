import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('tenant', {
    state: () => ({
        tenants: []
    }),
    actions: {
        async getAllTenant() {
            const r = await http.post(`/user/tenant/all`)
            this.tenants = r.data
        }
    }
})
