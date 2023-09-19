import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('area', {
    state: () => ({
        areas: []
    }),
    actions: {
        async getAllArea() {
            const r = await http.post(`/user/area/all`)
            this.areas = [{code: '', name: '请选择行政区'}, ...r.data]
        }
    }
})
