import { ref, onMounted } from 'vue'
import http from '@/utils/http.js'

export default function(id) {
    const detail = ref({
        productDto: {
            name: '',
            productKey: '',
            areaName: ''
        }
    })

    onMounted(async () => {
        const r =  await http.get(`/tenant/device/get/${id}`)
        detail.value = r.data
    })

    return {
        detail
    }
}