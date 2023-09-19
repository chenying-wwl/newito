import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('device', {
    state: () => ({
        devices: []
    }),
    actions: {
        async getAllDevice() {
            const r = await http.post(`/user/device/all`)
            this.devices = r.data
        }
    }
})
