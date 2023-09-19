import { defineStore } from 'pinia'
import http from '@/utils/http.js'

export default defineStore('product', {
    state: () => ({
        products: []
    }),
    actions: {
        async getAllProduct() {
            const r = await http.post(`/user/product/all`)
            this.products = r.data
        }
    }
})
