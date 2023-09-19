import { defineStore } from 'pinia'

export default defineStore('tenant', {
    state: () => ({
        name: '',
        tenantId: '',
        token: ''
    }),
    actions: {
        setInfo(name, tenantId, token) {
            this.name = name
            this.tenantId = tenantId
            this.token = token
        }
    },
    persist: true
})

