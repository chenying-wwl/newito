import { reactive, ref, onMounted, watch } from 'vue'
import http from '@/utils/http.js'
import useTenant from '@/store/tenant'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'
import useProduct from '@/store/product'

export default function() {

    const tenantStore = useTenant()
    const productStore = useProduct()
    const { tenantId } = storeToRefs(tenantStore)

    const router = useRouter()
    const provinces = ref([])
    const cities = ref([])
    const areas = ref([])
    // 控制是否展示地区，因为可能有直辖市，只有两级
    const showArea = ref(true)
    // 直辖市编码
    const municipality = reactive([110000, 120000, 310000, 500000])

    const modelRef = reactive({
        name: '',
        province: '',
        city: '',
        area: ''
    })

    // 获取所有的省份
    const getProvince = async () => {
        const r = await http.get(`/tenant/area/parentId/0`)
        provinces.value = r.data
        modelRef.province = provinces.value[0].code

        // 如果为直辖市，只有两级
        if(municipality.indexOf(modelRef.province) > -1) {
            showArea.value = false
        }

        getCities(modelRef.province)
    }

    // 获取城市
    const getCities = async code => {
        const r = await http.get(`/tenant/area/parentId/${code}`)
        cities.value = r.data
        modelRef.city = cities.value[0].code
        if(showArea.value) {
            // 获取城市
            getArea(modelRef.city)
        }
    }

    // 获取地区
    const getArea = async code => {
        const r = await http.get(`/tenant/area/parentId/${code}`)
        areas.value = r.data
        modelRef.area = areas.value[0].code
    }

    onMounted(() => {
        getProvince()  // 获取省份信息
    })

    // 切换省份
    watch(
        [() => modelRef.province, () => modelRef.city],
        (n,o) => {
            // 省份和城市都有值，并且变化了，不是最开始
            if(o[0] && o[1]) {
                // 如果省份没变，那说明是城市变化了
                if(o[0] === n[0]) {
                    // 如果不是直辖市，就需要获取地区了
                    if(municipality.indexOf(modelRef.province) === -1) {
                        getArea(modelRef.city)
                    }
                }
                if(o[1] === n[1]) {
                    // 表示变为了直辖市，地区不展示
                    if(municipality.indexOf(modelRef.province) > -1) {
                        showArea.value = false
                    }else {
                        showArea.value = true
                    }
                    getCities(modelRef.province)
                }
            }
        },
        {immediate: false}
    )

    const addProduct = async () => {
        let areaCode = null
        // 如果为直辖市，那么就取城市的code码, 否则取地区的码
        if(municipality.indexOf(modelRef.province) > -1) {
            areaCode = modelRef.city
        }else {
            areaCode = modelRef.area
        }
        await http.post(`/tenant/product/add`, {tenantId: tenantId.value, name: modelRef.name, areaCode})
        message.success('产品添加成功')
        // 重新获取一下设备，放到状态管理中
        productStore.getAllProducts()
        setTimeout(() => {
            router.push('/home/product')
        }, 500)
    }

    return {
        modelRef,
        addProduct,
        provinces,
        cities,
        areas,
        showArea
    }
}