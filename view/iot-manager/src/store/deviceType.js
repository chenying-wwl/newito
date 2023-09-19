import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('deviceTypes', {
    state: () => ({
        deviceTypes: []
    }),
    actions: {
        async getAllDeviceTypes() {
            const r = await http.post(`/user/device-type/all`)
            this.deviceTypes = [{id: '', name: '请选择设备类型'}, ...r.data]
        }
    }
})
