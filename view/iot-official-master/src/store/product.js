import { defineStore } from 'pinia'
import http from '@/utils/http.js'
import useTenant from './tenant.js'
import store from '@/store'
import { storeToRefs } from 'pinia'

export default defineStore('product', {
    state: () => ({
        products: []
    }),
    actions: {
        async getAllProducts() {
            const tenantStore = useTenant(store)
            const { tenantId } = storeToRefs(tenantStore)
            const r = await http.get(`/tenant/product/list?tenantId=${tenantId.value}`)
            this.products =  r.data.records
        },
        getSingle(id) {
            return this.products.find(p => p.id === id)
        },
        flip(cur, limit = 5) {
            // 开始索引
            const begin = limit * (cur - 1)
            const end = limit * cur
            return this.products.slice(begin, end)
        }
    },
    getters: {
        total() {
            return this.products.length
        }
    }
})
